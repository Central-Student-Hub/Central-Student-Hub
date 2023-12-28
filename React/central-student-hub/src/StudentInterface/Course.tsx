import React, { useState, useEffect } from 'react';
import { Accordion, AccordionButton, AccordionIcon, AccordionItem, AccordionPanel, Box, Button,
    Card, CardBody, Divider, Drawer, DrawerBody, DrawerCloseButton, DrawerContent,
    DrawerFooter, DrawerHeader, DrawerOverlay, Tab, TabList, TabPanel,
    TabPanels, Text,Tabs, useDisclosure, Link, Container, List, ListItem, CardHeader, Heading, Stack, StackDivider } from '@chakra-ui/react';
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

const Course: React.FC = () => {
  const [courses, setCourses] = useState<CourseReturn[]>([]);
  const [currentCourse,setCurrentCourse] = useState<CourseReturn>()
  const [materialPaths,setMaterialPaths] = useState<string[]>([])
  const [assignmnets,setAssignmnets] = useState<AssignmentsReturn[]>([])

  const btnRef = React.useRef()
  const courseApi = new CourseApi();
  const navigate = useNavigate();

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

    
  }, [currentCourse]);

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
                          {materialPath}
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
            <Card marginBottom={2}>
              <CardBody>
                <Text>it seems i caught a cold. i will need to rest. please see the lecture on this link&nbsp; 
                <Link color='teal.500' href='https://www.youtube.com/watch?v=9r_Xd5X-QHI&t=2s' target='blank'>
                  [FDBS] - Ch20 - Transaction Processing Concepts - YouTube
                </Link>
                </Text>
              </CardBody>
            </Card>
            <Card marginBottom={2}>
              <CardBody>
                <Text>it seems i caught a cold. i will need to rest. please see the lecture on this link&nbsp; 
                <Link color='teal.500' href='https://www.youtube.com/watch?v=9r_Xd5X-QHI&t=2s' target='blank'>
                  [FDBS] - Ch20 - Transaction Processing Concepts - YouTube
                </Link>
                </Text>
              </CardBody>
            </Card>
            <Card marginBottom={2}>
              <CardBody>
                <Text>it seems i caught a cold. i will need to rest. please see the lecture on this link&nbsp; 
                <Link color='teal.500' href='https://www.youtube.com/watch?v=9r_Xd5X-QHI&t=2s' target='blank'>
                  [FDBS] - Ch20 - Transaction Processing Concepts - YouTube
                </Link>
                </Text>
              </CardBody>
            </Card>
          </TabPanel>
        </TabPanels>
      </Tabs>
    </Container>
  );
};

export default Course;