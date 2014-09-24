package edu.chenjie1;

class Spy { 
 // instance data for spies 
 private String name; 
 private String title; 
 private String location; 
 private String password; 
 
 public Spy() { 
 } 
 
 public String getPassword() { 
 return password; 
 } 
 
 public void setPassword(String password) { 
 this.password = password; 
 } 
 
 public String getLocation() { 
 return location; 
 } 
 
 public String getName() { 
 return name; 
 } 
 
 public String getTitle() { 
 return title; 
 } 
 
 public void setLocation(String location) { 
 this.location = location; 
 } 
 
 public void setName(String name) { 
 this.name = name; 
 } 
 
 public void setTitle(String title) { 
 this.title = title; 
 } 
 
 public Spy(String name){
     this.name = name;
 }
 
 public Spy(String name, String title, String location, String password) { 
 this.name = name; 
 this.title = title; 
 this.location = location; 
 this.password = password; 
 } 
 
 public Spy(String name, String title, String location) { 
 this.name = name; 
 this.title = title; 
 this.location = location; 
 } 
 
 public String toXML() { 
 StringBuffer xml = new StringBuffer(); 
 
 xml.append("<spy>"); 
 xml.append("<name>" + name + "</name>"); 
 xml.append("<title>" + title + "</title>"); 
 xml.append("<location>" + location + "</location>"); 
 xml.append("<password>" + password + "</password>"); 
 xml.append("</spy>"); 
 
 return xml.toString(); 
 
 } 

} 
