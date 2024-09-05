import { Component } from '@angular/core';
import { Course } from '../models/course.model';

@Component({
  selector: 'app-course-selection',
  templateUrl: './course-selection.component.html',
  styleUrls: ['./course-selection.component.css']
})
export class CourseSelectionComponent {
  courses: Course[]; // Assume this is populated from your backend

  selectCourse(course: Course) {
    // Implement your course selection logic here
  }
}