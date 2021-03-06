package com.project.batch_study.domain;

import com.project.batch_study.utils.TestUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Test
    @DisplayName("데이터 추가용")
    void insertData() {
        for (int i = 0 ; i < 50 ; ++i ){
            Teacher teacher = Teacher.builder()
                    .age((int) TestUtil.getRandomLong(30, 45))
                    .name(TestUtil.getRandomString(10))
                    .classNumber((int) TestUtil.getRandomLong(1, 10))
                    .build();
            teacherRepository.save(teacher);
        }
    }

    @Test
    @DisplayName("Student와 Teacher 함께 추가하는 용도")
    void insertTeacherWithStudent() {
        int classNumber = (int) TestUtil.getRandomLong(1, 10);

        Teacher teacher = Teacher.builder()
                .age((int) TestUtil.getRandomLong(30, 45))
                .name(TestUtil.getRandomString(10))
                .classNumber(classNumber)
                .build();

        for (int i = 0 ; i < 10 ; ++i ){
            Student student = Student.builder()
                    .age((int) TestUtil.getRandomLong(30, 45))
                    .name(TestUtil.getRandomString(10))
                    .classNumber(classNumber)
                    .build();
            studentRepository.save(student);
            teacher.addStudent(student);
        }

        teacherRepository.save(teacher);

    }

}
