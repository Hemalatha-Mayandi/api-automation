package org.example.domain.libraryUd;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Libcourses {
    private String instructor;
    private String url;
    private String services;
    private String expertise;

    private Courses courses;
    private String linkedIn;

   /* @Getter
    @Setter
    public class Courses {
        private List<WebAutomation> webAutomation;
        private List<Api> api;
        private List<Mobile> mobile;
    }*/
  /*  @Getter
    @Setter
    public class WebAutomation {
        private String courseTitle;
        private String price;
    }*/
  /*  @Getter
    @Setter
    public class Api {
        private String courseTitle;
        private String price;
    }*/
 /*   @Getter
    @Setter
    public class Mobile {
        private String courseTitle;
        private String price;
    }*/
}
