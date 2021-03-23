// --== CS400 File Header Information ==--
// Name: <Eagan Kutz
// Email: <EjKutz@gmail.com>
// Team: <Blue>
// Group: <BF>
// TA: <Brianna Cochran>
// Lecturer: <Gary Dahl>
// Notes to Grader: <optional extra notes>
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
public class Backend extends RedBlackTree<Delivery>{
  java.util.Date date=new java.util.Date();
  RedBlackTree<Delivery> finalTree= new RedBlackTree<Delivery>();
  List<Delivery> dlist;
  
  public Backend (String filepath) {
    DataDeliveryReader read = new DataDeliveryReader();
    Path newpath= null;
     newpath.resolve(filepath);
    List<Delivery> dlist= new ArrayList<Delivery>();
    try {
      dlist = read.getDeliveryObjects(newpath);
    } catch (IOException | ParseException e) {
    }
    for (int x=0;dlist.size()<x;x++) {
      date=new java.util.Date();
     dlist.get(x).setOrderDate(date);;
      finalTree.insert(dlist.get(x));
     }
    }
  public void addDelivery(Delivery newdel){
    finalTree.insert(newdel);
}
  public void addDelivery(List <Delivery> newdel){
    for(int x=0;x<newdel.size();x++) {
      finalTree.insert(newdel.get(x));
    }
}
/*new Delivery with imputs of item location, fee and date deliverd, calculates order placed time and 
 * difference between date delivered and current time returns how many days till delivery
@ param String Item String location, int fee, and String date in dd/mm/yyyy format
@returns days till delivery
*/
  public int addDelivery(String item, String location, int feeDelivery ,String datestr) {
      Date now=new  Date();
    Date delivery=new  Date();
    try {
      delivery = new SimpleDateFormat("dd/MM/yyyy").parse(datestr);
    } catch (ParseException e) {
      System.out.println("Delivery Addition Failed imporoper date format.");
      return -1;
    }
    int day = (int) ((delivery.getTime()-now.getTime())/ (86400000) % 365); 
    finalTree.insert(new Delivery(item, day, feeDelivery, location, delivery, null, now));
    return day;
  }
  @Override
  public String toString() {
 // use the inorder Iterator that we get by calling the iterator method above
    // to generate a string of all values of the tree in (ordered) in-order
    // traversal sequence
    Iterator<Delivery> treeNodeIterator = this.iterator();
    StringBuffer sb = new StringBuffer();
    sb.append("[ ");
    if (treeNodeIterator.hasNext())
        sb.append(treeNodeIterator.next());
    while (treeNodeIterator.hasNext()) {
        Delivery data = treeNodeIterator.next();
        sb.append(", ");
        sb.append(data.toString());
    }
    sb.append(" ]");
    return sb.toString();
}
  
  /*Gets delivery based on itemname
   * @returns Delivery with ItemName
   * @param Item name to search for
   */
  public Delivery getDelivery(String itemName){
    Iterator<Delivery> treeNodeIterator = this.iterator();
    if (treeNodeIterator.hasNext()) {
      Delivery curritem =treeNodeIterator.next();
      if(curritem.getItemName().equals(itemName))
        return curritem;
    }
  while (treeNodeIterator.hasNext()) {
    Delivery curritem =treeNodeIterator.next();
    if(curritem.getItemName().equals(itemName))
      return curritem;
  }
return null;
  }
  /*Gets delivery based on date
   * @returns List with all deliveries on that date
   * @param string of date in dd/mm/yyyy format
   */
  public List<Delivery> getDeliverydate(String datestr){
    Date delivery=new  Date();
    List <Delivery>dlist=new ArrayList<Delivery>();
    try {
      delivery = new SimpleDateFormat("dd/MM/yyyy").parse(datestr);
    } catch (ParseException e) {
      System.out.println("Delivery Addition Failed imporoper date format.");
      return null;
    }
    Iterator<Delivery> treeNodeIterator = this.iterator();
    if (treeNodeIterator.hasNext()) {
      Delivery curritem =treeNodeIterator.next();
      int day = (int) ((curritem.getDeliverOnDate().getTime()-delivery.getTime())/ (86400000) % 365);
      if(day==0)
        dlist.add(curritem);
    }
  while (treeNodeIterator.hasNext()) {
    Delivery curritem =treeNodeIterator.next();
    int day = (int) ((curritem.getDeliverOnDate().getTime()-delivery.getTime())/ (86400000) % 365);
    if(day==0)
      dlist.add(curritem);
  }
    return dlist;
    
}
  /*Gets delivery based on date
   * @returns List with all deliveries on that date
   * @param date of delivery
   */
  public Delivery getDeliveryOn(Date date){
    Iterator<Delivery> treeNodeIterator = this.iterator();
    if (treeNodeIterator.hasNext()) {
      Delivery curritem =treeNodeIterator.next();
      if(curritem.getDeliverOnDate().equals(date))
        return curritem;
    }
  while (treeNodeIterator.hasNext()) {
    Delivery curritem =treeNodeIterator.next();
    if(curritem.getDeliverOnDate().equals(date))
      return curritem;
  }
  return null;
}
}
