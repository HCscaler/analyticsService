package com.knowit.AnalyticsService.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    
    private Integer id;

    private String title;
    
    private String description;

    private TaskStatus status;
    
    private LocalDate creationDate;

    private LocalDate dueDate;
    
    private int userId;

    private Integer projectId;

    private List<Comment> commnts;
}
