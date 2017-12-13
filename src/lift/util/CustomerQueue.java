package lift.util;

import lift.model.customer.Customer;

import java.util.*;

/**
 * 顾客优先队列类
 */
public class CustomerQueue<T extends Customer> extends PriorityQueue<Customer> {
    public CustomerQueue() {
            super();
        }

    public CustomerQueue(int initialCapacity) {
            super(initialCapacity);
        }

    public CustomerQueue(Comparator<? super Customer> comparator) {
            super(comparator);
        }

    public CustomerQueue(int initialCapacity, Comparator<? super Customer> comparator) {
            super(initialCapacity, comparator);
        }

    public CustomerQueue(Collection<? extends Customer> c) {
            super(c);
        }

    public CustomerQueue(PriorityQueue<? extends Customer> c) {
            super(c);
        }

    public CustomerQueue(SortedSet<? extends Customer> c) {
        super(c);
    }

    @Override
    public boolean add(Customer customer) {
        return super.add(customer);
    }

    @Override
    public boolean offer(Customer customer) {
        return super.offer(customer);
    }

    @Override
    public Customer peek() {
        return super.peek();
    }

    @Override
    public boolean remove(Object o) {
        return super.remove(o);
    }

    @Override
    public boolean contains(Object o) {
        return super.contains(o);
    }

    @Override
    public Object[] toArray() {
        return super.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return super.toArray(a);
    }

    @Override
    public Iterator<Customer> iterator() {
        return super.iterator();
    }

    @Override
    public int size() {
        return super.size();
    }

    @Override
    public void clear() {
        super.clear();
    }

    @Override
    public Customer poll() {
        return super.poll();
    }

    @Override
    public Comparator<? super Customer> comparator() {
        return super.comparator();
    }
}