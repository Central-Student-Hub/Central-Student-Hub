import { Text, Table, TableCaption, TableContainer, Tbody, Td, Tfoot, Th, Thead, Tr } from '@chakra-ui/react';
import React from 'react';
import './Grades.css';

const Grades: React.FC = () => {
  return (<div>

            <TableContainer  className='gradesTable'>
                <Table variant='unstyled' colorScheme='messenger'>
                    <TableCaption placement='top' fontSize={20} backgroundColor='#03315F'
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
                        <Tr>
                            <Td>CSE111</Td>
                            <Td>Probability</Td>
                            <Td>3</Td>
                            <Td>A+</Td>
                        </Tr>
                        <Tr>
                            <Td>CSE121</Td>
                            <Td>Programming1</Td>
                            <Td>3</Td>
                            <Td>A+</Td>
                        </Tr>
                        <Tr>
                            <Td>CSE131</Td>
                            <Td>Digital Logic</Td>
                            <Td>3</Td>
                            <Td>A+</Td>
                        </Tr>
                        <Tr>
                            <Td>CSE141</Td>
                            <Td>Data Structure</Td>
                            <Td>3</Td>
                            <Td>A</Td>
                        </Tr>
                        <Tr>
                            <Td>CSE211</Td>
                            <Td>HCI</Td>
                            <Td>2</Td>
                            <Td>A</Td>
                        </Tr>
                        <Tr>
                            <Td>CSE221</Td>
                            <Td>SoftWare Engineering</Td>
                            <Td>3</Td>
                            <Td>A+</Td>
                        </Tr>
                    </Tbody>
                </Table>
            </TableContainer>

            <TableContainer className='gradesTable' >
                <Table variant='unstyled' colorScheme='messenger'>
                    <TableCaption placement='top' fontSize={20} backgroundColor='#03315F'
                     fontWeight={'bold'} color={'White'}>
                        Semester 2
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
                        <Tr>
                            <Td>CSE111</Td>
                            <Td>Probability</Td>
                            <Td>3</Td>
                            <Td>A+</Td>
                        </Tr>
                        <Tr>
                            <Td>CSE121</Td>
                            <Td>Programming1</Td>
                            <Td>3</Td>
                            <Td>A+</Td>
                        </Tr>
                        <Tr>
                            <Td>CSE131</Td>
                            <Td>Digital Logic</Td>
                            <Td>3</Td>
                            <Td>A+</Td>
                        </Tr>
                        <Tr>
                            <Td>CSE141</Td>
                            <Td>Data Structure</Td>
                            <Td>3</Td>
                            <Td>A</Td>
                        </Tr>
                        <Tr>
                            <Td>CSE211</Td>
                            <Td>HCI</Td>
                            <Td>2</Td>
                            <Td>A</Td>
                        </Tr>
                        <Tr>
                            <Td>CSE221</Td>
                            <Td>SoftWare Engineering</Td>
                            <Td>3</Td>
                            <Td>A+</Td>
                        </Tr>
                    </Tbody>
                </Table>
            </TableContainer>

            <TableContainer className='gradesTable'>
                <Table variant='unstyled' colorScheme='messenger'>
                    <TableCaption placement='top' fontSize={20} backgroundColor='#03315F'
                     fontWeight={'bold'} color={'White'}>
                        Semester 3
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
                        <Tr>
                            <Td>CSE111</Td>
                            <Td>Probability</Td>
                            <Td>3</Td>
                            <Td>A+</Td>
                        </Tr>
                        <Tr>
                            <Td>CSE121</Td>
                            <Td>Programming1</Td>
                            <Td>3</Td>
                            <Td>A+</Td>
                        </Tr>
                        <Tr>
                            <Td>CSE131</Td>
                            <Td>Digital Logic</Td>
                            <Td>3</Td>
                            <Td>A+</Td>
                        </Tr>
                        <Tr>
                            <Td>CSE141</Td>
                            <Td>Data Structure</Td>
                            <Td>3</Td>
                            <Td>A</Td>
                        </Tr>
                        <Tr>
                            <Td>CSE211</Td>
                            <Td>HCI</Td>
                            <Td>2</Td>
                            <Td>A</Td>
                        </Tr>
                        <Tr>
                            <Td>CSE221</Td>
                            <Td>SoftWare Engineering</Td>
                            <Td>3</Td>
                            <Td>A+</Td>
                        </Tr>
                    </Tbody>
                </Table>
            </TableContainer>

            <TableContainer className='gradesTable'>
                <Table variant='unstyled' colorScheme='messenger'>
                    <TableCaption placement='top' fontSize={20} backgroundColor='#03315F'
                     fontWeight={'bold'} color={'White'}>
                        Semester 4
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
                        <Tr>
                            <Td>CSE111</Td>
                            <Td>Probability</Td>
                            <Td>3</Td>
                            <Td>A+</Td>
                        </Tr>
                        <Tr>
                            <Td>CSE121</Td>
                            <Td>Programming1</Td>
                            <Td>3</Td>
                            <Td>A+</Td>
                        </Tr>
                        <Tr>
                            <Td>CSE131</Td>
                            <Td>Digital Logic</Td>
                            <Td>3</Td>
                            <Td>A+</Td>
                        </Tr>
                        <Tr>
                            <Td>CSE141</Td>
                            <Td>Data Structure</Td>
                            <Td>3</Td>
                            <Td>A</Td>
                        </Tr>
                        <Tr>
                            <Td>CSE211</Td>
                            <Td>HCI</Td>
                            <Td>2</Td>
                            <Td>A</Td>
                        </Tr>
                        <Tr>
                            <Td>CSE221</Td>
                            <Td>SoftWare Engineering</Td>
                            <Td>3</Td>
                            <Td>A+</Td>
                        </Tr>
                    </Tbody>
                </Table>
            </TableContainer>
  
  
        </div>
    );
};

export default Grades;
