import { Box, Flex,Text, Image,Badge,Stat,StatLabel,StatNumber,
        Heading, Stack, Card, CardBody, StackDivider, CardHeader} from '@chakra-ui/react'
import React from 'react'
import './StudentProfile.css';
import { color } from 'framer-motion';


export default function StudentProfile() {

    return (

        <div className='studentBadge'>
            <Flex>
                <Flex marginRight='10'>
                    <Image borderRadius='full' boxSize='120px' src='https://bit.ly/sage-adebayo'/>
                    <Box ml='6'>
                        <Text fontWeight='bold'  fontSize='xl'>
                            Mohammed Adel
                            <Badge size='xl' ml='1' colorScheme='green' >
                                Pro Max
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
                    <CardHeader backgroundColor='#1F1F1F' color='white' borderRadius='10px 10px 0px 0px'>
                        <Heading size='md'>Student contacts</Heading>
                    </CardHeader>

                    <CardBody backgroundColor='#f7f7f7' borderColor='#1F1F1F' borderWidth='thin' borderRadius='0px 0px 10px 10px'>                        <Stack divider={<StackDivider />} spacing='4'>
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
        </div>
    )
}


  