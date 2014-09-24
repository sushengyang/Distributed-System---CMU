package edu.chenjie1;
import java.util.Collection; 
import java.util.Iterator; 
import java.util.Map; 
import java.util.TreeMap; 
 
public class SpyList { 
 
 private Map tree = new TreeMap(); 
 
 private static SpyList spyList = new SpyList(); 
 
 private SpyList() { 
 } 
 
 public static SpyList getInstance() { 
 return spyList;
  } 
 public void add(Spy s) { 
 tree.put(s.getName(), s); 
 } 
 public void delete(Spy s) { 
 tree.remove(s.getName()); 
 } 
 public Spy get(String userID) { 
 return (Spy) tree.get(userID); 
 } 
 
 public Collection getList() { 
 return tree.values(); 
 } 
 
 public String toString() { 
 
 StringBuffer representation = new StringBuffer(); 
 Collection c = getList(); 
 Iterator sl = c.iterator(); 
 while(sl.hasNext()) { 
 Spy spy = (Spy)sl.next(); 
 representation.append("Name: " + spy.getName()+" Title: " + spy.getTitle()+ 
 " Location: " + spy.getLocation()); 
 } 
 return representation.toString(); 
 } 
 
 public String toXML() { 
 StringBuffer xml = new StringBuffer(); 
 xml.append("<spylist>\n"); 
 
 Collection c = getList(); 
 Iterator sl = c.iterator(); 
 while(sl.hasNext()) { 
 Spy spy = (Spy)sl.next(); 
 xml.append(spy.toXML()); 
 } 
 // Now, close 
 xml.append("</spylist>"); 
 
 System.out.println("Spy list: " + xml.toString()); 
 return xml.toString(); 
 } 
} 
