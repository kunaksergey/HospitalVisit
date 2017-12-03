package ua.shield.entity;

import ua.shield.enum_.NewsType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name="news")
public class News {
    @Id
    private Integer id;
    @Column(name="message")
    private String message;
    private LocalDateTime date;
    private NewsType type;
}
