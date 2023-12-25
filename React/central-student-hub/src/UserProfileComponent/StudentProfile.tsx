import {
    Box, Flex, Text, Image, Badge, Stat, StatLabel, StatNumber,
    Heading, Stack, Card, CardBody, StackDivider, CardHeader
} from '@chakra-ui/react'
import React, { useState } from 'react'
import './StudentProfile.css';
import { color } from 'framer-motion';
import { StudentProfileInfo } from '../Models/StudentProfileInfo';


export default function StudentProfile() {

    const [profile, setProfile] = useState<StudentProfileInfo>({
        firstName: "Mohamed",
        lastName: "Adel",
        biography: "I am an Enthausiastic Engineer. and I hate Mohammed Esssam",
        profilePictureUrl: "https://i.imgur.com/5N5J7bP.png",
        major: "Computer Engineering",
        minor: "Electrical Engineering",
        noOfHours: 12,
        gpa: 3.5,
        level: 3,
        contacts: [
            {
                label: "Email",
                data: "JohnDoe@gmail.com"
            },
            {
                label: "Phone",
                data: "123-456-7890"
            },
            {
                label: "Address",
                data: "1234 Main St, Springfield, IL 62701"
            }
        ],
        warnings: [
            {
                warningId: 1,
                reason: "Low GPA",
                date: new Date("2021-03-01")
            },
            {
                warningId: 2,
                reason: "Low GPA",
                date: new Date("2021-04-01")
            }
        ],
        grades: [
            {
                courseCode: "CSE111",
                courseName: "DS",
                grade: 90,
                numberOfHours: 3
            },
            {
                courseCode: "CSE113",
                courseName: "OS",
                grade: 80,
                numberOfHours: 3
            },
            {
                courseCode: "CSE112",
                courseName: "AI",
                grade: 70,
                numberOfHours: 3
            }
        ],
    });

    return (

        <div className='studentBadge'>
            <Flex>
                <Flex marginRight='10'>
                    <Image borderRadius='full' boxSize='120px' src='https://bit.ly/sage-adebayo' />
                    <Box ml='6'>
                        <Text fontWeight='bold' fontSize='xl'>
                            {profile?.firstName} {profile?.lastName}
                            {/* <Badge size='xl' ml='1' colorScheme='green' >
                                Pro Max
                            </Badge> */}
                        </Text>
                        <Text fontSize='md'>Software Engineer</Text>
                    </Box>
                </Flex>

                <Stat>
                    <StatLabel>GPA</StatLabel>
                    <StatNumber>{profile?.gpa}</StatNumber>
                </Stat>
            </Flex>

            <Flex>
                <Card className='StudentInfo' borderRadius='10' zIndex={-1} marginRight={45}>
                    <CardHeader backgroundColor='#1F1F1F' color='white' borderRadius='10px 10px 0px 0px'>
                        <Heading size='md'>Student Info</Heading>
                    </CardHeader>

                    <CardBody backgroundColor='#f7f7f7' borderColor='#1F1F1F' borderWidth='thin' borderRadius='0px 0px 10px 10px'>
                        <Stack divider={<StackDivider />} spacing='4'>
                            <Box>
                                <Heading size='xs' textTransform='uppercase' >
                                    Biography
                                </Heading>
                                <Text pt='2' fontSize='sm'>
                                    {profile?.biography}
                                </Text>
                            </Box>
                            <Box>
                                <Heading size='xs' textTransform='uppercase'>
                                    Major
                                </Heading>
                                <Text pt='2' fontSize='sm'>
                                    {profile?.major}
                                </Text>
                            </Box>
                            <Box>
                                <Heading size='xs' textTransform='uppercase'>
                                    Minor
                                </Heading>
                                <Text pt='2' fontSize='sm'>
                                    {profile?.minor}
                                </Text>
                            </Box>
                            <Box>
                                <Heading size='xs' textTransform='uppercase'>
                                    Number of Hours
                                </Heading>
                                <Text pt='2' fontSize='sm'>
                                    {profile?.noOfHours}
                                </Text>
                            </Box>
                            <Box>
                                <Heading size='xs' textTransform='uppercase'>
                                    Level
                                </Heading>
                                <Text pt='2' fontSize='sm'>
                                    { profile?.level }
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
                    <CardHeader backgroundColor='#1F1F1F' color='white' borderRadius='10px 10px 0px 0px'>
                        <Heading size='md'>Student Contact Info</Heading>
                    </CardHeader>

                    <CardBody backgroundColor='#f7f7f7' borderColor='#1F1F1F' borderWidth='thin' borderRadius='0px 0px 10px 10px'>
                        <Stack divider={<StackDivider />} spacing='4'>
                            {
                                profile?.contacts.map(contact => <>
                                    <Heading size='xs' textTransform='uppercase' >
                                        { contact.label }
                                    </Heading>
                                    <Text pt='2' fontSize='sm'>
                                        { contact.data }
                                    </Text>
                                </>)
                            }
                        </Stack>
                    </CardBody>
                </Card>
            </Flex>
        </div>
    )
}


