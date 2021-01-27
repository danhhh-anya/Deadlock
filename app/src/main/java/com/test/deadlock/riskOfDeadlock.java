package com.test.deadlock;

public class riskOfDeadlock implements Runnable {
    private static class Resource {
    }
    /*Ситуация: 2 студента сидят за одной партой.
     Задание заключается в самостоятельном наптсание конспектов.
     На парте есть только 1 тетрадь и 1 книга.
     Первый студент берет тетрадь, второй книгу.
     Никто из них не в состояние написать конспект из-за недостающих предметов.*/

    private final Resource notebook = new Resource();
    private final Resource book = new Resource();

    public void writeNotes() {
        synchronized (notebook) {
            System.out.println(Thread.currentThread().getName() + "take notebook to write notes");
            synchronized (book) {
                System.out.println(Thread.currentThread().getName() + "take book to write notes");
                System.out.println(Thread.currentThread().getName()
                        + "is writing notes right now");
            }
        }
    }

    public void run() {
        writeNotes();

    }

    public static void main(String[] args) {
        riskOfDeadlock job = new riskOfDeadlock();
        Thread person1 = new Thread(job, "Student1");
        Thread person2 = new Thread(job, "Student2");
        person1.start();
        person2.start();
    }
}
