package org.example._24week;

import java.util.Objects;

public class Test {

    public static void main(String[] args) {
        Person person1 = new Person("정민욱", 26, 178);
        Person person2 = new Person("정민욱", 26, 178);
        Object name = "A";
        String name2 = "A";

        System.out.println(name.hashCode());
        System.out.println(name2.hashCode());

        System.out.println(person1.equals(person2));
        System.out.println(Objects.hash(person1));
        System.out.println(Objects.hash(person2));
        System.out.println(Objects.hashCode(person1));
        System.out.println(Objects.hashCode(person2));
    }
    public static class Person{
        String name;
        int age;
        int height;

        public Person(String name, int age, int height) {
            this.name = name;
            this.age = age;
            this.height = height;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Person person = (Person) o;
            return age == person.age && height == person.height && Objects.equals(name, person.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, age, height);
        }
    }
}

