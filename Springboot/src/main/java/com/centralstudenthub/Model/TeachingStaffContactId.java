package com.centralstudenthub.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class TeachingStaffContactId implements Serializable {
    private String label;
    private Integer teacherId;
}
