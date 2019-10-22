package com.example.final_homework;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class DoubleCount {
    static private ArrayList<String> histroy;
    static private ArrayList<String> histroyback;
    static private Queue<String> father;
    static private Queue<String> now;
    static private Queue<String> fatherback;
    static private Queue<String> nowback;
    static private int Best;
    static private int Back;

    static private void solver(String x) {
        char[] arr = x.toCharArray();
        int m = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '9') {
                m = i;
                break;
            }
        }
        ArrayList<Integer> change = new ArrayList<>();
        switch (m) {
            case 0:
                change.add(1);
                change.add(3);
                break;
            case 1:
                change.add(0);
                change.add(2);
                change.add(4);
                break;
            case 2:
                change.add(1);
                change.add(5);
                break;
            case 3:
                change.add(0);
                change.add(4);
                change.add(6);
                break;
            case 4:
                change.add(1);
                change.add(3);
                change.add(5);
                change.add(7);
                break;
            case 5:
                change.add(2);
                change.add(4);
                change.add(8);
                break;
            case 6:
                change.add(3);
                change.add(7);
                break;
            case 7:
                change.add(4);
                change.add(6);
                change.add(8);
                break;
            case 8:
                change.add(5);
                change.add(7);
                break;
        }
        for (int i : change) {
            char[] a = new char[9];
            for (int j = 0; j < 9; j++) {
                a[j] = arr[j];
            }
            char temp;
            temp = a[m];
            a[m] = a[i];
            a[i] = temp;
            String str = String.valueOf(a);
            boolean mark = true;
            for (String y : histroy) {
                if (y.equals(str))
                    mark = false;
            }
            if (mark) {
                histroy.add(str);
                now.add(str);
            }
        }
    }

    static private void solverback(String x) {
        char[] arr = x.toCharArray();
        int m = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '9') {
                m = i;
                break;
            }
        }
        ArrayList<Integer> change = new ArrayList<>();
        switch (m) {
            case 0:
                change.add(1);
                change.add(3);
                break;
            case 1:
                change.add(0);
                change.add(2);
                change.add(4);
                break;
            case 2:
                change.add(1);
                change.add(5);
                break;
            case 3:
                change.add(0);
                change.add(4);
                change.add(6);
                break;
            case 4:
                change.add(1);
                change.add(3);
                change.add(5);
                change.add(7);
                break;
            case 5:
                change.add(2);
                change.add(4);
                change.add(8);
                break;
            case 6:
                change.add(3);
                change.add(7);
                break;
            case 7:
                change.add(4);
                change.add(6);
                change.add(8);
                break;
            case 8:
                change.add(5);
                change.add(7);
                break;
        }
        for (int i : change) {
            char[] a = new char[9];
            for (int j = 0; j < 9; j++) {
                a[j] = arr[j];
            }
            char temp;
            temp = a[m];
            a[m] = a[i];
            a[i] = temp;
            String str = String.valueOf(a);
            boolean mark = true;
            for (String y : histroyback) {
                if (y.equals(str))
                    mark = false;
            }
            if (mark) {
                histroyback.add(str);
                nowback.add(str);
            }
        }
    }

    public DoubleCount(String test) {
        histroy = new ArrayList<>();
        histroyback = new ArrayList<>();
        father = new LinkedList<>();
        now = new LinkedList<>();
        fatherback = new LinkedList<>();
        nowback = new LinkedList<>();
        solver(test);
        nowback.add("123456789");
        Best = 0;
        Back = 0;
        while (true) {
            if (father.isEmpty()) {
                while (!now.isEmpty()) {
                    father.add(now.poll());
                }
                Best++;
            }
            if (fatherback.isEmpty()) {
                while (!nowback.isEmpty()) {
                    fatherback.add(nowback.poll());
                }
            }
            while (!father.isEmpty()) {
                String str = father.poll();
                for (String strs : fatherback) {
                    if (str.equals(strs)) {
                        return;
                    }
                }
                solver(str);
            }
            Back++;
            while (!fatherback.isEmpty()) {
                String y = fatherback.poll();
                for (String strs : now) {
                    if (y.equals(strs)) {
                        return;
                    }
                }
                solverback(y);
            }

        }
    }

    int getBest() {
        return Best + Back;
    }
}
