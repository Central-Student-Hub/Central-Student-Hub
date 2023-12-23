import { Avatar, Box, Flex,Text, Image,Badge,Stat,StatLabel,StatNumber,
        Heading, Stack, Card, CardBody, StackDivider, CardHeader, TableContainer, Table, Thead, TableCaption, Tr, Th, Tbody, Td, Tfoot} from '@chakra-ui/react'
import React from 'react'
import './StudentProfile.css';


export default function StudentProfile() {


    return (

        <div className='studentBadge'>
            <Flex>
                <Flex marginRight='10'>
                    <Image borderRadius='full' boxSize='120px' src='https://bit.ly/sage-adebayo'/>
                    <Box ml='6'>
                        <Text fontWeight='bold'  fontSize='xl'>
                            Ziad Reda
                            <Badge size='xl' ml='1' colorScheme='green' >
                                New
                            </Badge>
                        </Text>
                        <Text fontSize='md'>Software Engineer</Text>
                    </Box>
                </Flex>
            
                <Stat>
                    <StatLabel>GPA</StatLabel>
                    <StatNumber>4</StatNumber>
                </Stat>
            </Flex>

            <Flex>
                <Card className='StudentInfo' borderRadius='10' zIndex={-1} marginRight={45}>
                    <CardHeader backgroundColor='#03315F' color='white' borderRadius='10px 10px 0px 0px'>
                        <Heading size='md'>Student Info</Heading>
                    </CardHeader>

                    <CardBody backgroundColor='#F2F9FF'>
                        <Stack divider={<StackDivider />} spacing='4'>
                        <Box>
                            <Heading size='xs' textTransform='uppercase' >
                                Biography
                            </Heading>
                            <Text pt='2' fontSize='sm'>
                                I am an Enthausiastic Engineer. and I hate Mohammed Esssam
                            </Text>
                        </Box>
                        <Box>
                            <Heading size='xs' textTransform='uppercase'>
                                Major
                            </Heading>
                            <Text pt='2' fontSize='sm'>
                                Computer and Systems
                            </Text>
                        </Box>
                        <Box>
                            <Heading size='xs' textTransform='uppercase'>
                                Minor
                            </Heading>
                            <Text pt='2' fontSize='sm'>
                                Electrical
                            </Text>
                        </Box>
                        <Box>
                            <Heading size='xs' textTransform='uppercase'>
                                Number of Hours
                            </Heading>
                            <Text pt='2' fontSize='sm'>
                                17
                            </Text>
                        </Box>
                        <Box>
                            <Heading size='xs' textTransform='uppercase'>
                                Level
                            </Heading>
                            <Text pt='2' fontSize='sm'>
                                4
                            </Text>
                        </Box>
                        <Box>
                            <Heading size='xs' textTransform='uppercase'>
                                Gender
                            </Heading>
                            <Text pt='2' fontSize='sm'>
                                Male
                            </Text>
                        </Box>
                        </Stack>
                    </CardBody>
                </Card>

                <Card className='StudentInfo2' borderRadius='10' zIndex={-1}>
                    <CardHeader backgroundColor='#03315F' color='white' borderRadius='10px 10px 0px 0px'>
                        <Heading size='md'>Student contacts</Heading>
                    </CardHeader>

                    <CardBody backgroundColor='#F2F9FF'>
                        <Stack divider={<StackDivider />} spacing='4'>
                        <Box>
                            <Heading size='xs' textTransform='uppercase' >
                                Biography
                            </Heading>
                            <Text pt='2' fontSize='sm'>
                                I am an Enthausiastic Engineer. and I hate Mohammed Esssam
                            </Text>
                        </Box>
                        <Box>
                            <Heading size='xs' textTransform='uppercase'>
                                Major
                            </Heading>
                            <Text pt='2' fontSize='sm'>
                                Computer and Systems
                            </Text>
                        </Box>
                        <Box>
                            <Heading size='xs' textTransform='uppercase'>
                                Minor
                            </Heading>
                            <Text pt='2' fontSize='sm'>
                                Electrical
                            </Text>
                        </Box>
                        <Box>
                            <Heading size='xs' textTransform='uppercase'>
                                Number of Hours
                            </Heading>
                            <Text pt='2' fontSize='sm'>
                                17
                            </Text>
                        </Box>
                        <Box>
                            <Heading size='xs' textTransform='uppercase'>
                                Level
                            </Heading>
                            <Text pt='2' fontSize='sm'>
                                4
                            </Text>
                        </Box>
                        <Box>
                            <Heading size='xs' textTransform='uppercase'>
                                Gender
                            </Heading>
                            <Text pt='2' fontSize='sm'>
                                Male
                            </Text>
                        </Box>
                        </Stack>
                    </CardBody>
                </Card>
            </Flex>

            <TableContainer borderRadius='10' zIndex={-1} marginTop={8} borderWidth={2} 
            borderColor={'#03315F'} backgroundColor='#F2F9FF'>
                <Table variant='unstyled' colorScheme='messenger'>
                    <TableCaption placement='top' fontSize={20} backgroundColor='#03315F'
                     fontWeight={'bold'} color={'White'}>
                        Course Grades
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
    )
}


  