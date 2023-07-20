package cz.tc.learn.hibernate.gs;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@Entity
public class Employee {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Task> tasks;
    @ElementCollection
    private List<String> phoneNumbers;

    public String getName() {
        return name;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public static Employee create(String name, List<String> phoneNumbers, Task... tasks) {
        Employee e = new Employee();
        e.name = name;
        if (tasks != null) {
            e.tasks = Arrays.asList(tasks);
        }
        e.phoneNumbers = phoneNumbers;
        return e;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tasks=" + tasks +
                ", phoneNumbers=" + phoneNumbers +
                '}';
    }
}
