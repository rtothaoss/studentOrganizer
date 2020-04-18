import React, { Component } from 'react';
import { getAllStudents } from './client'

class App extends Component {

  state = {
    students: []
  }

  componentDidMount() {
    this.fetchStudents()
  }

  fetchStudents = () => {
    getAllStudents()
      .then(res => res.json()
        .then(students => {
          console.log(students)
          this.setState({
            students
          })

        }))
  }

  render() {

    const { students } = this.state

    let studentsList = <h2>No Students Found!</h2>

    if(students && students.length) {

    studentsList = students.map(student => (
        <div key={student.studentId}>
          <h2>{student.studentId}</h2>
          <p>{student.firstName}</p>
          <p>{student.lastName}</p>
          <p>{student.gender}</p>
          <p>{student.email}</p>
        </div>
      ))
    }

    return (
      <div>
       
        {studentsList}
      </div>
    );
  }
}

export default App;