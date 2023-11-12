package main.java.view;

import main.java.controller.ExamGradeList;
import main.java.controller.ExamList;
import main.java.controller.StudentList;
import main.java.model.Exam;
import main.java.model.ExamGrade;
import main.java.model.Staff;
import main.java.controller.TimeTableList;
import main.java.model.TimeTable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class TeacherView {

    Staff loggedInTeacher;
    StudentList studentList = new StudentList();
    ExamList examList = new ExamList();
    ExamGradeList examGradeList = new ExamGradeList();
    TimeTableList timeTableList = new TimeTableList();
    
    PrintWriter out = null;
    BufferedReader in = null;
    public TeacherView(Staff teacher){
        loggedInTeacher = teacher;
    }

    public void login(PrintWriter out, BufferedReader in) throws IOException {
        this.out = out;
        this.in=in;

        out.println("successfully logged in as teacher: "+loggedInTeacher.getUserName());

        int choice = -1;
        while(choice !=0){
            out.println("__________enter teacher choice___________");
            out.println("1.Student operations\n0.exit the program");
            out.println("@r#");
            choice = Integer.parseInt(in.readLine());
            if (choice ==1){
                studentOps();
            }
        }// end of while
    }

    public void studentOps() throws IOException {

        int choice = -1;
        while(choice !=99){
            out.println("_______Student Operations________");
            out.println("1.Add exam\n2.print number of Students\n3. print TimeTable\n4.print exams\n5.add grade exam\n6.delete exam \n0.back to main");
            out.println("@r#");
            choice = Integer.parseInt(in.readLine());
            if (choice == 1){
                addExam();
            }else if (choice ==2){
                out.println("the number of students is: "+studentList.getNumberOfStudents());
            }else if (choice ==3){
                timeTableList.printTimeTable(out);
            }else if (choice==4){
                examList.printExam(out);
            }else if (choice==5){
                gradeExam();
            }else if (choice==6){
                deleteExam();
            }else if (choice ==0){ break;}
        }// end of while
    }

    public void addExam(){

        int subjectId;
        String  examDesc, date;

        try {
            out.println("Enter SubjectId");
            out.println("@r#");
            subjectId = Integer.parseInt(in.readLine());
            out.println("Enter Exam name");
            out.println("@r#");
            examDesc = in.readLine();
            out.println("Enter date");
            out.println("@r#");
            date = in.readLine();
            int id = studentList.getMaxId()+1;
            Exam exam = new Exam(id, subjectId,examDesc, date);
            examList.addExam(exam);
            out.println("subject:"+examDesc +"date: "+date);
        }catch(Exception e){
            out.println("invalid inputs");
        }
    }

    public void gradeExam(){

        int studentId, mark, examId;

        try {
            out.println("Enter studentId");
            out.println("@r#");
            studentId = Integer.parseInt(in.readLine());
            out.println("Enter mark");
            out.println("@r#");
            mark = Integer.parseInt(in.readLine());
            out.println("Enter examId");
            out.println("@r#");
            examId = Integer.parseInt(in.readLine());
            int id = studentList.getMaxId()+1;
            ExamGrade examGrade = new ExamGrade(id, studentId,mark, examId);
            examGradeList.addExamGrade(examGrade);
            out.println("studentId:"+studentId +"mark: "+mark+"examId:"+examId);
        }catch(Exception e){
            out.println("invalid inputs");
        }
    }

    public void deleteExam() throws IOException {

        out.println("Enter Exam name to remove");
        out.println("@r#");
        String examName = in.readLine();
        examList.deleteGrade(examName);
    }
}
