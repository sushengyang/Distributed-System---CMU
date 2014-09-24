// ======================= .java ==========================================
package org.myorg;
import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.*;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.util.*;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;


public class OaklandCrimeStats extends Configured implements Tool {

        public static class OaklandCrimeStatsMap extends Mapper<LongWritable, Text, Text, IntWritable>
        {
                private final static IntWritable one = new IntWritable(1);
                private Text word = new Text();
                

		
		// rangeX and rangeY are the distance within 1000 ft range of Oakland destination. 
		// oaklandX and oaklandY are the coordinate of Oakland destination.
		// Pythagorean theorem to calculate the distance. 
                @Override
                public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException
                {	
                        String line = value.toString();
			String[] rows = line.split("\t");
				
			if (isNumeric(rows[0]) && (isNumeric(rows[1]))){
			double rangeX = Double.parseDouble(rows[0]);
			double rangeY = Double.parseDouble(rows[1]);
			double oaklandX = 1354326.897; 
			double oaklandY = 411447.7828;
			if (Math.sqrt(Math.pow(oaklandX - rangeX,2) + Math.pow(oaklandY - rangeY,2)) < 1000) { 
			word.set("Oakland Crime:");
			context.write(word, one);
			}
		}

                }
		
		// A method used to test whether the input string is numeric
		public static boolean isNumeric(String str)  
		{  
  		try  
  		{  
    			double d = Double.parseDouble(str);  
  		}		  
  			catch(NumberFormatException nfe)  
  		{  
    			return false;  
  		}  
  			return true;  
		}
        }
        
        public static class OaklandCrimeStatsReducer extends Reducer<Text, IntWritable, Text, IntWritable>
        {
                public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException
                {
                        int sum = 0;
                        for(IntWritable value: values)
                        {
                                sum += value.get();
                        }
                        context.write(key, new IntWritable(sum));
                }
                
        }
        
        public int run(String[] args) throws Exception  {
               
                Job job = new Job(getConf());
                job.setJarByClass(OaklandCrimeStats.class);
                job.setJobName("crimestats");
                
                job.setOutputKeyClass(Text.class);
                job.setOutputValueClass(IntWritable.class);
                
                job.setMapperClass(OaklandCrimeStatsMap.class);
                job.setCombinerClass(OaklandCrimeStatsReducer.class);
                job.setReducerClass(OaklandCrimeStatsReducer.class);
                
                
                job.setInputFormatClass(TextInputFormat.class);
                job.setOutputFormatClass(TextOutputFormat.class);
                
                
                FileInputFormat.setInputPaths(job, new Path(args[0]));
                FileOutputFormat.setOutputPath(job, new Path(args[1]));
                
                boolean success = job.waitForCompletion(true);
                return success ? 0: 1;
        }
        
       
        public static void main(String[] args) throws Exception {
                // TODO Auto-generated method stub
                int result = ToolRunner.run(new OaklandCrimeStats(), args);
                System.exit(result);
        }
       
} 
