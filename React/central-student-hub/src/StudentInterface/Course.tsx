import React, { useState, useEffect } from 'react';
import { ApiRequester } from '../Services/ApiRequester.ts'
import { Accordion, AccordionButton, AccordionIcon, AccordionItem, AccordionPanel, Box, Button,
    Card, CardBody, Divider, Drawer, DrawerBody, DrawerCloseButton, DrawerContent,
    DrawerFooter, DrawerHeader, DrawerOverlay, Tab, TabList, TabPanel,
    TabPanels, Text,Tabs, useDisclosure, Link, Container, List, ListItem, CardHeader, Heading, Stack, StackDivider } from '@chakra-ui/react';
import {Link as RLink, useNavigate } from 'react-router-dom';

    
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


const Course: React.FC = () => {
  const [courses, setCourses] = useState<CourseType[]>([]);
  const apiRequester = new ApiRequester();
  const btnRef = React.useRef()


  const navigate = useNavigate();

  const handleRedirect = (paramValue) => {
    // Use navigate to redirect to the target page with parameters
    navigate(`/teaching-staff-profile/${paramValue}`);
  };

  // // Fetch courses from the backend
  // useEffect(() => {
  //   const getCourses = async () => await apiRequester.retrieveSemesterCourses();
  //   getCourses()
  //     .then((courses) => setCourses(courses))
  //     .catch((error) => console.error(error));
  // }, []);

  const handleSelectCourse = () =>{

  }



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
                  <ListItem>
                    <Button color="gray.600" fontSize="xs" width={250}>
                      Math
                    </Button>
                  </ListItem>
                  <ListItem>
                    <Button color="gray.600" fontSize="xs" width={250}>
                      Data Base
                    </Button>
                  </ListItem>
                  <ListItem>
                    <Button color="gray.600" fontSize="xs" width={250}>
                      Software
                    </Button>
                  </ListItem>
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
                        Math
                      </Text> 
                    </Box>
                    <Box>
                      <Heading size='xs' textTransform='uppercase'>
                        Teaching Staff
                      </Heading>
                      <Text pt='2' fontSize='sm'>
                      <RLink to='/teaching-staff-profile/5'>
                        Elmongi                     
                      </RLink>
                      </Text>
                    </Box>
                    <Box>
                      <Heading size='xs' textTransform='uppercase'>
                        Course Description
                      </Heading>
                      <Text pt='2' fontSize='sm'>
                        hi i am a course
                      </Text>
                    </Box>
                    <Box>
                      <Heading size='xs' textTransform='uppercase'>
                        Credit Hours
                      </Heading>
                      <Text pt='2' fontSize='sm'>
                        17
                      </Text>
                    </Box>
                  </Stack>
                </CardBody>
              </Card>
          </TabPanel>
          <TabPanel>
          <Accordion defaultIndex={[0]} allowMultiple>
              <AccordionItem>
                <h2>
                  <AccordionButton>
                    <Box as="span" flex='1' textAlign='left'>
                      Section 1 title
                    </Box>
                    <AccordionIcon />
                  </AccordionButton>
                </h2>
                <AccordionPanel pb={4}>
                  Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod
                  tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim
                  veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea
                  commodo consequat.
                </AccordionPanel>
              </AccordionItem>

              <AccordionItem>
                <h2>
                  <AccordionButton>
                    <Box as="span" flex='1' textAlign='left'>
                      Section 2 title
                    </Box>
                    <AccordionIcon />
                  </AccordionButton>
                </h2>
                <AccordionPanel pb={4}>
                  Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod
                  tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim
                  veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea
                  commodo consequat.
                </AccordionPanel>
              </AccordionItem>
            </Accordion>
          </TabPanel>
          <TabPanel>
            <Accordion defaultIndex={[0]} allowMultiple>
              <AccordionItem>
                <h2>
                  <AccordionButton>
                    <Box as="span" flex='1' textAlign='left'>
                      Section 1 title
                    </Box>
                    <AccordionIcon />
                  </AccordionButton>
                </h2>
                <AccordionPanel pb={4}>
                  Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod
                  tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim
                  veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea
                  commodo consequat.
                </AccordionPanel>
              </AccordionItem>

              <AccordionItem>
                <h2>
                  <AccordionButton>
                    <Box as="span" flex='1' textAlign='left'>
                      Section 2 title
                    </Box>
                    <AccordionIcon />
                  </AccordionButton>
                </h2>
                <AccordionPanel pb={4}>
                  Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod
                  tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim
                  veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea
                  commodo consequat.
                </AccordionPanel>
              </AccordionItem>
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