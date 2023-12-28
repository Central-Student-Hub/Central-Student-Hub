import { Table, TableCaption, TableContainer, Tbody, Td, Th, Thead, Tr } from '@chakra-ui/react';
import React, { useEffect, useState } from 'react';
import './Grades.css';
import { Grade } from '../Models/Grade.ts'
import { UserProfileApi } from '../Services/UserProfileApi.ts';

function MyTable({ semesterGrades }) {

    return (
        <TableContainer className='gradesTable'>
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

    const [grades, setGrades] = useState<Grade[]>([]);
    const api = new UserProfileApi();

    useEffect(() => {
        const getGrades = async () => await api.getStudentGrades();

        getGrades()
            .then((grades) => setGrades(grades))
            .catch((err) => console.log(err));
    }, []);

    const semesters = grades.map(course =>
        <Tr>
            <Td>{course.courseCode}</Td>
            <Td>{course.courseName}</Td>
            <Td>{course.noOfHours}</Td>
            <Td>{course.grade}</Td>
        </Tr>
    )

    return (
        <div>
            {semesters.map(sem => <MyTable semesterGrades={sem} />)}
        </div>
    );
};

export default Grades;
