import React, { Component } from 'react';
import { getAllStudents } from './client';
import { Table, Avatar, Spin } from 'antd';
import { LoadingOutlined } from '@ant-design/icons';
import Container from './components/Container';
import Footer from './components/Footer';


const getIndicator = () => <LoadingOutlined style={{ fontSize: 24 }} spin />;

class App extends Component {

  state = {
    students: [],
    isFetching: false
  }

  componentDidMount() {
    this.fetchStudents()
  }

  fetchStudents = () => {
    this.setState({
      isFetching: true
    })
    getAllStudents()
      .then(res => res.json()
        .then(students => {
          console.log(students)
          this.setState({
            students,
            isFetching: false
          })

        }))
  }

  render() {

    const { students, isFetching } = this.state

    

    let studentsList = <h2>No Students Present</h2>

    if(isFetching) {
      return (
      <Container >
        <Spin indicator={getIndicator()}/>
      </Container>
      )
    }

    if (students && students.length) {

      const columns = [
        {
          title: '',
          key: 'avatar',
          render: (text, student) => (
            <Avatar size='large'>{`${student.firstName.charAt(0).toUpperCase()}${student.lastName.charAt(0).toUpperCase()}`}</Avatar>
          )
        },
        {
          title: 'StudentId',
          dataIndex: 'studentId',
          key: 'studentId'
        },
        {
          title: 'First Name',
          dataIndex: 'firstName',
          key: 'firstName'
        },
        {
          title: 'Last Name',
          dataIndex: 'lastName',
          key: 'lastName'
        },
        {
          title: 'Email',
          dataIndex: 'email',
          key: 'email'
        },
        {
          title: 'Gender',
          dataIndex: 'gender',
          key: 'gender'
        }
      ];

      studentsList = <Table dataSource={students} columns={columns} rowKey='studentId' pagination={false} />

    }

    return (
      <Container>

        {studentsList}

        <Footer numberOfStudents = {students.length}/>

      </Container>




    );
  }
}

export default App;