package core.basesyntax;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteList<E> {
    private List<E> list = new ArrayList<>();
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public void add(E element) {
        lock.writeLock().lock();
        try {
            list.add(element);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public E get(int index) {
        lock.readLock().lock();
        E result;
        try {
            result = list.get(index);
        } finally {
            lock.readLock().unlock();
        }
        return result;
    }

    public int size() {
        lock.readLock().lock();
        int result;
        try {
            result = list.size();
        } finally {
            lock.readLock().unlock();
        }
        return result;
    }
}
