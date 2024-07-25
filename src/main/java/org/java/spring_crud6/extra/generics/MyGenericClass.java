package org.java.spring_crud6.extra.generics;

public class MyGenericClass {

    public static void main(String[] args) {

        MyOptional<Integer> intOptional = new MyOptional<>(10);

        MyList<Integer> intList = new MyList<>();
        MyList<String> strList = new MyList<>();

        intList.add(1);
        intList.add(2);
        intList.add(3);
        intList.add(4);
        intList.add(5);

        strList.add("one");
        strList.add("two");
        strList.add("three");
        strList.add("four");
        strList.add("five");

        System.out.println(intList);
        System.out.println(strList);

        intList.remove(3);
        strList.remove("three");

        System.out.println(intList);
        System.out.println(strList);
    }

    public static class MyOptional<E> {

        private E value;

        public MyOptional() {
            this.value = null;
        }

        public MyOptional(E value) {
            this.value = value;
        }

        public E get() {
            return value;
        }

        public boolean isPresent() {
            return value != null;
        }

        public boolean isEmpty() {
            return value == null;
        }

        public void set(E value) {
            this.value = value;
        }

        public void clear() {
            this.value = null;
        }

        @Override
        public String toString() {
            return "MyOptional {\n" +
                    "\tvalue=" + value + "\n" +
                    "}";
        }
    }

    public static class MyList<E> {

        private E[] elements;
        private int size = 10;

        public MyList() {
            elements = (E[]) new Object[10];
            size = 0;
        }

        public MyList(int capacity) {
            elements = (E[]) new Object[capacity];
            size = 0;
        }

        public void add(E e) {
            if (size == elements.length) {
                E[] temp = (E[]) new Object[elements.length * 2];
                System.arraycopy(elements, 0, temp, 0, elements.length);
                elements = temp;
            }
            elements[size++] = e;
        }

        public void remove(E e) {
            for (int i = 0; i < size; i++) {
                if (elements[i].equals(e)) {
                    System.arraycopy(elements, i + 1, elements, i, size - i - 1);
                    size--;
                    break;
                }
            }
        }

        @Override
        public String toString() {

            E[] temp = (E[]) new Object[size];
            System.arraycopy(elements, 0, temp, 0, size);
            E tmp = temp[0];

            return "MyList {\n" +
                    "\tclear elements=" + java.util.Arrays.toString(temp) + "\n" +
                    "\telements=" + java.util.Arrays.toString(elements) + "\n" +
                    "\tsize=" + size + "\n" +
                    "\ttype=" + tmp.getClass().getName() + "\n" +
                    "}";
        }
    }
}
