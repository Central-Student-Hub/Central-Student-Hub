import React, { useState, useEffect } from 'react';
import { Accordion, AccordionButton, AccordionIcon, AccordionItem, AccordionPanel, Box, Button,
    Card, CardBody, Divider, Drawer, DrawerBody, DrawerCloseButton, DrawerContent,
    DrawerFooter, DrawerHeader, DrawerOverlay, Tab, TabList, TabPanel,
    TabPanels, Text,Tabs, useDisclosure, Link, Container, List, ListItem, CardHeader, Heading, Stack, StackDivider, FormControl, FormLabel, Input, useToast } from '@chakra-ui/react';
import {Link as RLink, useNavigate } from 'react-router-dom';
import { CourseApi } from '../Services/CourseApi.ts';

    
// Enum for semesters
enum Semester {
  Fall = "Fall",
  Summer = "Summer",
  Spring = "Spring"
}

// Type definition for course
export type CourseType = {
  code: string;
  name: string;
  description: string;
  creditHours: number;
  semester: Semester;
  maxSeats: number;
};

export type CourseReturn = {
  semCourseId:number;
  semCourseCode:string;
  semCourseName:string;
  semCourseDescription:string;
  semCourseCreditHours:number;
  teacherFirstName:string;
  teacherLastName:string;
  teacherId:number;
}

export type AssignmentsReturn = {
  assignmentId:number;
  assignmentName:string;
  assignmentDescription:string;
  assignmentDueDate:Date;
  assignmentMaterialPaths:string[];
}

export type AnnouncementsReturn = {
  announcementId:number;
  announcementName:string;
  description:string;
}

export type AssignmentAnswerReq ={
  assignmentId:number;
  answerPath:string;
}


const Course: React.FC = () => {
  const [courses, setCourses] = useState<CourseReturn[]>([]);
  const [currentCourse,setCurrentCourse] = useState<CourseReturn>()
  const [materialPaths,setMaterialPaths] = useState<string[]>([])
  const [assignmnets,setAssignmnets] = useState<AssignmentsReturn[]>([])
  const [announcements,setAnnouncements] = useState<AnnouncementsReturn[]>([])
  const [assignmentsAnswer,setAssignmentsAnswer] = useState<AssignmentAnswerReq[]>([])

  const btnRef = React.useRef()
  const courseApi = new CourseApi();
  const navigate = useNavigate();
  const toast = useToast();

  useEffect(() => {
    const fetchSemCourses = async () => await courseApi.getSemCourseByStudentId();
    fetchSemCourses()
      .then((courseRet) => setCourses(courseRet))
      .catch((error) => console.error(error))
  }, []);


  useEffect(() => {
    if(courses.length > 0)
      setCurrentCourse(courses[0]);
  }, [courses]);


  useEffect(() => {

    // Get Material Paths
    const fetchMaterialPath = async () => await courseApi.getMaterialPathByCourseId(currentCourse?.semCourseId);
    fetchMaterialPath()
      .then((MaterialPaths) => setMaterialPaths(MaterialPaths))
      .catch((error) => console.error(error))

    // Get Assignments
    const fetchAssignments = async () => await courseApi.getAssignmentsByCourseId(currentCourse?.semCourseId);
    fetchAssignments()
      .then((Assignments) => setAssignmnets(Assignments))
      .catch((error) => console.error(error))


    //Get Announecements
    const fetchAnnouncements = async () => await courseApi.getAnnouncementsByCourseId(currentCourse?.semCourseId);
    fetchAnnouncements()
      .then((Announcements) => setAnnouncements(Announcements))
      .catch((error) => console.error(error))
    
  }, [currentCourse]);


  const handleAnswerChange = (assignmentId,e) =>{
    const newArr = assignmentsAnswer.filter(assignmentAnswer => assignmentAnswer.assignmentId === assignmentId)

    if(newArr.length === 0)
      setAssignmentsAnswer([...assignmentsAnswer, {assignmentId:assignmentId,answerPath:e.target.value}])
    else
      setAssignmentsAnswer([...assignmentsAnswer.filter(assignmentAnswer => assignmentAnswer.assignmentId !== assignmentId),
                                 {assignmentId:assignmentId,answerPath:e.target.value}])
  }

  const handleAnswerSubmit = async (assignmentId) =>{
    console.log(assignmentsAnswer.find((x) => x.assignmentId === assignmentId))
    const bool = await courseApi.postAssignmentAnswer(assignmentsAnswer.find((x) => x.assignmentId === assignmentId))
    if(bool){
      toast({
        title: 'Accepted!',
        description: 'Answer Added Successfully',
        status: 'success',
        duration: 3000,
        isClosable: true,
    })
    }
    else{
      toast({
        title: 'Rejected',
        description: 'Adding Answer Rejected',
        status: 'error',
        duration: 3000,
        isClosable: true,
    })
    }

  }

  // const handleRedirect = (paramValue) => {
  //   // Use navigate to redirect to the target page with parameters
  //   navigate(`/teaching-staff-profile/${paramValue}`);
  // };

  function DrawerExample() {
    const { isOpen, onOpen, onClose } = useDisclosure()

    return (
      <>
        <Button ref={btnRef} colorScheme='teal' onClick={onOpen} >
          See all courses
        </Button>
        <Drawer
          isOpen={isOpen}
          placement='right'
          onClose={onClose}
          finalFocusRef={btnRef}
        >
          <DrawerOverlay/>
          <DrawerContent>
            <DrawerCloseButton />
            <DrawerHeader>My Courses</DrawerHeader>
            <DrawerBody>
              <Box>
                <List size="xl" variant="custom" spacing={5}>

                  {courses.map((semCourse,idx) => {
                    return(
                      <ListItem>
                        <Button color="gray.600" fontSize="xs" width={250} onClick={
                          () => setCurrentCourse(semCourse)
                        }>
                          {semCourse.semCourseName}
                        </Button>
                      </ListItem>
                    )
                  })}
                </List>
              </Box>
            </DrawerBody>
            <DrawerFooter>
          
            </DrawerFooter>
          </DrawerContent>
        </Drawer>
      </>
    )
  }

  return (
    <Container>
      <h1 className="text-2xl font-bold mb-4">Your Courses</h1>
      <DrawerExample/>
      <Divider orientation='horizontal' marginTop={4} colorScheme="linkedin"/>
      <Tabs width={1350}>
        <TabList>
          <Tab>Course Info</Tab>
          <Tab>Material</Tab>
          <Tab>Assignments</Tab>
          <Tab>Announcements</Tab>
        </TabList>

        <TabPanels>
          
          <TabPanel>
            <Card className='StudentInfo' borderRadius='10'>
                <CardHeader backgroundColor='#1F1F1F' color='white' borderRadius='10px 10px 0px 0px'>
                    <Heading size='md'>Course Info</Heading>
                </CardHeader>

                <CardBody backgroundColor='#f7f7f7' borderColor='#1F1F1F' borderWidth='thin' borderRadius='0px 0px 10px 10px'>
                  <Stack divider={<StackDivider />} spacing='4'>
                    <Box>
                      <Heading size='xs' textTransform='uppercase'>
                        Course Name
                      </Heading>
                      <Text pt='2' fontSize='sm'>
                        {currentCourse?.semCourseName}
                      </Text> 
                    </Box>
                    <Box>
                      <Heading size='xs' textTransform='uppercase'>
                        Teaching Staff
                      </Heading>
                      <Text pt='2' fontSize='sm'>
                      <RLink to={`/teaching-staff-profile/${currentCourse?.teacherId}`}>
                        {currentCourse?.teacherFirstName} {currentCourse?.teacherLastName}                      
                      </RLink>
                      </Text>
                    </Box>
                    <Box>
                      <Heading size='xs' textTransform='uppercase'>
                        Course Description
                      </Heading>
                      <Text pt='2' fontSize='sm'>
                        {currentCourse?.semCourseDescription}
                      </Text>
                    </Box>
                    <Box>
                      <Heading size='xs' textTransform='uppercase'>
                        Credit Hours
                      </Heading>
                      <Text pt='2' fontSize='sm'>
                        {currentCourse?.semCourseCreditHours}
                      </Text>
                    </Box>
                  </Stack>
                </CardBody>
              </Card>
          </TabPanel>

          <TabPanel>
            <Accordion defaultIndex={[0]} allowMultiple>
              { materialPaths.length>0 &&
                materialPaths.map(
                  (materialPath,idx) =>{
                    return (
                      <AccordionItem>
                        <h2>
                          <AccordionButton>
                            <Box as="span" flex='1' textAlign='left'>
                              Section {idx+1}
                            </Box>
                            <AccordionIcon />
                          </AccordionButton>
                        </h2>
                        <AccordionPanel pb={4}>
                          <Link color='teal.500' href={materialPath} target='blank'>
                            Click on the Link 
                          </Link>
                        </AccordionPanel>
                      </AccordionItem>                    
                    )
                  }
                )
              }
            </Accordion>
          </TabPanel>

          <TabPanel>
            <Accordion defaultIndex={[0]} allowMultiple>
              {assignmnets.length>0 &&
              assignmnets.map(
                (assignment,idx) => {
                  return(
                    <AccordionItem>
                    <h2>
                      <AccordionButton>
                        <Box as="span" flex='1' textAlign='left'>
                          Assignment {idx+1}
                        </Box>
                        <AccordionIcon />
                      </AccordionButton>
                    </h2>
                    <AccordionPanel pb={4}>
                      <Card>
                        <CardHeader>
                          <Heading size='md'>{assignment.assignmentName}</Heading>
                        </CardHeader>
    
                        <CardBody>
                          <Stack divider={<StackDivider />} spacing='4'>
                            <Box>
                              <Heading size='xs' textTransform='uppercase'>
                                Description
                              </Heading>
                              <Text pt='2' fontSize='sm'>
                                {assignment.assignmentDescription}
                              </Text>
                            </Box>
                            <Box>
                              <Heading size='xs' textTransform='uppercase'>
                                Due Date
                              </Heading>
                              <Text pt='2' fontSize='sm'>
                                {assignment.assignmentDueDate?.toDateString()}
                              </Text>
                            </Box>
                            <Box>
                              <Heading size='xs' textTransform='uppercase'>
                                Material Path
                              </Heading>
                              {assignment.assignmentMaterialPaths.map(
                                (link,index) =>{
                                  return(
                                    <Link color='teal.500' href={link} target='blank'>
                                      Link {index}
                                    </Link>
                                  )
                                }
                              )}
                            </Box>
                          </Stack>
                          
                          <form onSubmit={(e)=> { e.preventDefault(); handleAnswerSubmit(assignment.assignmentId) }}>
                            <FormControl>
                              <FormLabel>Enter your Answer Link:</FormLabel>
                              <Input
                                type="text"
                                onChange={(e) => handleAnswerChange(assignment.assignmentId,e)}
                              />
                            </FormControl>
                            <Button type="submit" mt={4} colorScheme="teal">
                              Submit
                            </Button>
                          </form>
                          
                        </CardBody>
                      </Card>
                    </AccordionPanel>
                  </AccordionItem>
                )
                }
              )}
            </Accordion>
          </TabPanel>

          <TabPanel>
            {announcements.length>0 &&
            announcements.map(
              announcement=>{
                return(
                  <Card marginBottom={2}>
                    <CardBody>
                      <Box>
                        <Heading size='xs' textTransform='uppercase'>
                          {announcement.announcementName}
                        </Heading>
                        <Text pt='2' fontSize='sm'>
                          {announcement.description}
                        </Text>
                      </Box>
                    </CardBody>
                  </Card>
                )
              }
            )}
          </TabPanel>

        </TabPanels>
      </Tabs>
    </Container>
  );
};

export default Course;