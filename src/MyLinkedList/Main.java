package MyLinkedList;


import org.junit.Test;

public class Main {
    @Test
    public void test(){
        MyLinkedList list = new MyLinkedList();

        list.add(15);
        list.add(16);
        list.add(10);

        System.out.println(list.find(10));
        System.out.println(list.find(15));
        System.out.println(list.find(16));

        list.delete(16);
        System.out.println(list.find(16));
        System.out.println("Done");
    }

}
