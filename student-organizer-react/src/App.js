import React, { Component } from 'react';
import { getAllStudents } from './client';
import { Table, Avatar, Spin, Modal, Empty, err } from 'antd';
import { LoadingOutlined } from '@ant-design/icons';
import Container from './components/Container';
import Footer from './components/Footer';
import AddStudentForm from './components/forms/AddStudentForm';
import * as notification from './components/Notification';


const getIndicator = () => <LoadingOutlined style={{ fontSize: 24 }} spin />;

class App extends Component {

  state = {
    students: [],
    isFetching: false,
    isAddStudentModalVisible: false
  }

  componentDidMount() {
    this.fetchStudents()
  }

  handleOk = () => {
    this.setState({
      isAddStudentModalVisible: false
    })
  }

  handleCancel = () => {
    this.setState({
      isAddStudentModalVisible: false
    })
  }

  showModal = () => {
    this.setState({
      isAddStudentModalVisible: true
    })
  }

  closeModal = () => {
    this.setState({
      isAddStudentModalVisible: false
    })
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
        .catch(error => {
          const message = error.error.message
          const description = error.error.error
          notification.errorNotification(message, description)
          this.setState({
            isFetching: false
          })
        })
  }

  render() {

    const { students, isFetching, isAddStudentModalVisible } = this.state

    

    let studentsList = <Empty description={<h1>No students found</h1>}/>

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

      studentsList = <Table  style={{marginBottom: '100px'}} dataSource={students} columns={columns} rowKey='studentId' pagination={false} />

    }

    return (
      <Container>

        {studentsList}

        <Modal
        title='Add new student'
        visible={isAddStudentModalVisible}
        onOk={this.handleOk}
        onCancel={this.handleCancel}
        width={1000}
        >
        <AddStudentForm 
          onSuccess={() => {
            this.closeModal();
            this.fetchStudents();
            }}
          onFailure={(err) => {
            // console.log(JSON.stringify(err))
            notification.errorNotification(err, 'Try a different email.');
          }}
        />
        </Modal>
        <Footer numberOfStudents = {students.length} handleStudentClick={this.showModal}/>

      </Container>




    );
  }
}

export default App;