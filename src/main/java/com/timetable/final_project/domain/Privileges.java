package com.timetable.final_project.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Privileges {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private boolean addHours;
    private boolean deleteHours;
    private boolean requestDeleteHours;
    private boolean overviewHours;
    private boolean calculateSalary;
    private boolean approveRegistration;

    @OneToMany(mappedBy = "privileges")
    List<Employee> employes;

    public boolean isApproveRegistration() {
        return approveRegistration;
    }

    public void setApproveRegistration(boolean approveRegistration) {
        this.approveRegistration = approveRegistration;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isAddHours() {
        return addHours;
    }

    public void setAddHours(boolean addHours) {
        this.addHours = addHours;
    }

    public boolean isDeleteHours() {
        return deleteHours;
    }

    public void setDeleteHours(boolean deleteHours) {
        this.deleteHours = deleteHours;
    }

    public boolean isRequestDeleteHours() {
        return requestDeleteHours;
    }

    public void setRequestDeleteHours(boolean requestDeleteHours) {
        this.requestDeleteHours = requestDeleteHours;
    }

    public boolean isOverviewHours() {
        return overviewHours;
    }

    public void setOverviewHours(boolean overviewHours) {
        this.overviewHours = overviewHours;
    }

    public boolean isCalculateSalary() {
        return calculateSalary;
    }

    public void setCalculateSalary(boolean calculateSalary) {
        this.calculateSalary = calculateSalary;
    }


}
