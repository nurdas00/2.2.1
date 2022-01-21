package hiber.model;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

@Entity
@Table(name = "cars")

public class Car {

    @Id
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private User user;


    @Column(name = "model")
    private String model;

    @Column(name = "series")
    private int series;

    public Car(){}

    public Car(int series, String model){
        this.series = series;
        this.model = model;
    }

    public Long getId(){return id;}

    public void setModel(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public int getSeries() {
        return series;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString(){
        return model;
    }
}
