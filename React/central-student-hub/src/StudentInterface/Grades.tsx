import {Table, TableCaption, TableContainer, Tbody, Td, Th, Thead, Tr } from '@chakra-ui/react';
import React from 'react';
import './Grades.css';
import {c} from '../Models/Grade.ts'

function MyTable({semesterGrades}){

    return(
        <TableContainer  className='gradesTable'>
            <Table variant='unstyled'>
                <TableCaption placement='top' fontSize={20} backgroundColor='#1F1F1F'
                    fontWeight={'bold'} color={'White'}>
                    Semester 1
                </TableCaption>
                <Thead>
                    <Tr>
                        <Th>Course Code</Th>
                        <Th>Course Name</Th>
                        <Th>Number Of Hours</Th>
                        <Th>Grade</Th>
                    </Tr>
                </Thead>
                <Tbody>
                    {semesterGrades}
                </Tbody>
            </Table>
        </TableContainer>
    );
}

const Grades: React.FC = () => {

    const semesters = c.map(semesterArray =>
        
        semesterArray.map(course => 
            <Tr>
                <Td>{course.courseCode}</Td>
                <Td>{course.courseName}</Td>
                <Td>{course.numberOfHours}</Td>
                <Td>{course.grade}</Td>
            </Tr>
        )
    )

  return (
        <div>
            {semesters.map(sem => <MyTable semesterGrades={sem}/>)}
        </div>
    );
};

export default Grades;
