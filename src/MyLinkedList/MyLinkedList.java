package MyLinkedList;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyLinkedList<E> implements List<E> {
    private Node<E> first;
    private Node<E> last;
    private int size = 0;

    private static class Node<E>{
        E element;
        Node<E> next;
        Node<E> previous;

        private Node(E element, Node<E> next, Node<E> previous){
            this.element = element;
            this.next = next;
            this.previous = previous;
        }
    }

    private void linkFirst(E element){
        Node<E> f = first;
        Node<E> newNode = new Node<>(element, null, f);
        first = newNode;
        if(f == null){
            last = newNode;
        }
        else{
            f.next = newNode;
        }
        size++;
    }

    private void linkLast(E element){
        Node<E> l = last;
        Node<E> newNode = new Node<>(element, null, l);
        last = newNode;
        if (l == null){
            first = newNode;
        }
        else{
            l.next = newNode;
        }
        size++;
    }

    public boolean addFirst(E element){
        linkFirst(element);
        return true;
    }

    public boolean addLast(E element){
        linkLast(element);
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o)>=0;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        int i = 0;
        for (Node<E> x = first; x != null; x = x.next)
            result[i++] = x.element;
        return result;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }


    @Override
    public boolean add(E e) {
        linkLast(e);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if(o == null){
            for(Node<E> i = first; i!=null; i = i.next){
                if (i.element == null){
                    unlink(i);
                    return true;
                }
            }
            size--;
        }
        else{
            for(Node<E> i = first; i!=null; i = i.next){
                if (o.equals(i.element)){
                    unlink(i);
                    return true;
                }
            }
            size--;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        int index1 =  0;
        int index2 = 0;
        for(Node<E> i = first; i!=null; i = i.next){
            if(c.contains(i.element)){
                index1++;
            }
            index2++;
        }
        return index1 == index2;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return addAll(size, c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        checkPositionIndex(index);

        Object[] a = c.toArray();
        int numNew = a.length;
        if (numNew == 0)
            return false;

        Node<E> pred, succ;
        if (index == size) {
            succ = null;
            pred = last;
        } else {
            succ = node(index);
            pred = succ.previous;
        }

        for (Object o : a) {
            @SuppressWarnings("unchecked") E e = (E) o;
            Node<E> newNode = new Node<>(e, null, pred);
            if (pred == null)
                first = newNode;
            else
                pred.next = newNode;
            pred = newNode;
        }

        if (succ == null) {
            last = pred;
        } else {
            pred.next = succ;
            succ.previous = pred;
        }

        size += numNew;
        return true;}

    private Node<E> node(int index) {
        if (index < (size >> 1)) {
            Node<E> x = first;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            Node<E> x = last;
            for (int i = size - 1; i > index; i--)
                x = x.previous;
            return x;
        }
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for(Node<E> i = first; i!=null; i = i.next){
            if(c.contains(i.element)){
                Node<E> pred = i.previous;
                Node<E> next = i.next;
                if (pred == null){
                    first = next;
                }
                else if(next == null){
                    last = pred;
                }
                else{
                    pred.next = next;
                    next.previous = pred;
                }
                size--;
            }
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        for(Node<E> i = first; i != null; i = i.next) {
            if(!c.contains(i.element)) {
                E element = i.element;
                Node<E> pred = i.previous;
                Node<E> next = i.next;
                if(pred == null) {
                    first = next;
                }
                else if(next == null) {
                    last = pred;
                }
                else {
                    element = null;
                    next.previous = pred;
                    pred.next = next;
                }
                size--;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        for(Node<E> i = first; i!=null; i = i.next){
            i.element = null;
        }
        size = 0;
    }

    public E getFirst(int index) {
        Node<E> f = first;
        return f.element;
    }

    public E getLast(int index) {
        Node<E> l = last;
        return l.element;
    }

    @Override
    public E get(int index) {
        int ind = 0;
        for (Node<E> i = first; i != null; i = i.next) {
            if (ind == index) {
                return i.element;
            }
            ind++;
        }
        return null;
    }

    @Override
    public E set(int index, E element) {
        checkElementIndex(index);
        Node<E> x = node(index);
        E oldVal = x.element;
        x.element = element;
        return oldVal;
    }

    @Override
    public void add(int index, E element) {
        int ind = 0;
        if(index == size){
            linkLast(element);
        }
        else{
            for(Node<E> i = first; i!=null; i = i.next){
                if(ind == index){
                    Node<E> node = new Node<>(i.element, i.next, i.previous);
                    Node<E> newNode = new Node<>(element, node, node.previous);
                    if (newNode == null){
                        first = newNode;
                    }
                    else{
                        node.previous.next = newNode;
                    }
                    size++;
                }
            }
        }
    }

    @Override
    public E remove(int index) {
        int index1 = 0;
        for(Node<E> i = first; i!=null;i = i.next){
            if (index1 == index){
                return unlink(i);
            }
            index1++;

        }
        size--;
        return null;
    }

    @Override
    public int indexOf(Object o) {
        int index = 0;
        if(o==null){
            for(Node<E> i = first; i!=null; i = i.next){
                if(i.element == null){
                    return index;
                }
                index++;
            }
        }
        else{
            for (Node<E> i = first; i!=null; i = i.next) {
                if(o.equals(i.element)){
                    return index;
                }
                index++;

            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int index = 0;
        for(Node<E> i = first; i!=null; i = i.next){
            if(o.equals(i.element)){
                return index;
            }
        }
        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    private E unlink(Node<E> x){
        Node<E> next = x.next;
        Node<E> previous = x.previous;
        E element = x.element;

        if(previous == null){
            first = next;
        }
        else {
            previous.next = next;
            x.previous = null;
        }

        if(next == null){
            last = previous;
        }
        else{
            next.previous = previous;
            x.next = null;
        }

        x.element = null;
        size--;
        return element;
    }

    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size;
    }

    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

}
