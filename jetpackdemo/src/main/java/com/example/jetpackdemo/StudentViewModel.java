package com.example.jetpackdemo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * @Author: shiwenming
 * @Date: 2022-06-07
 * @Description:
 */
public class StudentViewModel extends ViewModel {

    public int age;

    public StudentViewModel() {
        System.out.println("调用了无参构造方法");
    }

    public StudentViewModel(int age) {
        this.age = age;
        System.out.println("调用了有参构造方法");
    }

    LiveData<Student> data = new MutableLiveData<>();

}
