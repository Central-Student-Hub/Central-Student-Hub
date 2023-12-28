import { Box, Flex,Text, Image,Stat,StatLabel,StatNumber,
        Heading, Stack, Card, CardBody, StackDivider, CardHeader, Button, Input, Textarea, useToast, Modal, ModalOverlay, ModalCloseButton, ModalContent, ModalHeader, ModalBody, ModalFooter, useDisclosure}from '@chakra-ui/react'

import { AddIcon } from '@chakra-ui/icons'
import React, { useEffect, useState } from 'react'
import './StudentProfile.css';
import {s} from '../Models/StudentProfileInfo.ts'
import { UserProfileApi } from '../Services/UserProfileApi.ts';
  

export default function StudentProfile() {

    const[studInfo,setStudInfo] = useState(s)
    const[oldStudInfo,setOldStudInfo] = useState(s)
    const[editing,setEditing] = useState(false)
    const[updatePhoto,setUpdatePhoto] = useState(false)

    const api = new UserProfileApi();
    const toast = useToast()

    // const infoKeys = ["major","minor","level","noOfHours","gpa"]

    useEffect(() => {
        if (document.cookie === "") {
            window.location.href = 'http://localhost:3000/login';
        }

        const fetchProfile = async () => await api.getStudentProfile();
        fetchProfile()
          .then((studInfo) => setStudInfo(studInfo))
          .catch((error) => console.error(error))
      }, []);

    function BasicUsage() {
        const { isOpen, onOpen, onClose } = useDisclosure()

        return (
          <>
            <Button className='addImageButton' colorScheme='gray' variant='solid' size='xs' onClick={onOpen}>
                <AddIcon/>
            </Button>
      
            <Modal isOpen={isOpen} onClose={onClose}>
              <ModalOverlay />
              <ModalContent>
                <ModalHeader>Profile picture</ModalHeader>
                <ModalCloseButton />
                <ModalBody>
                    A picture helps people recognize you and lets
                    you know when you’re signed in to your account
                </ModalBody>
                <ModalFooter>
                
                  <Button colorScheme='blue' mr={3}>
                    Update Profile Image
                  </Button>
                  
                </ModalFooter>
              </ModalContent>
            </Modal>
          </>
        )
    }


    const handleEditProfile = () => {
        setOldStudInfo(studInfo)
        setEditing(!editing)
    }

    const handleSaveChanges = () => {
        if(studInfo.firstName === "" || studInfo.lastName === "" ){
            toast({
                title: 'Rejected',
                description: 'You can\'t have First or Last name empty',
                status: 'error',
                duration: 3000,
                isClosable: true,
            })
            return
        }
        setEditing(!editing)

        const newContacts = studInfo.contacts.filter(cont => cont.data !== "" && cont.label!=="")
        setStudInfo(
            {
                ...studInfo,
                contacts:newContacts
            }
        );

        api.updateStudentProfile(studInfo!)
        .then(
            r =>
            toast({
                title: 'Accepted',
                description: 'Edited Profile Succesfully',
                status: 'success',
                duration: 4000,
                isClosable: true,
            })
        )
        .catch((error) => 
            toast({
                title: 'Rejected',
                description: 'Failed to edited Profile',
                status: 'error',
                duration: 4000,
                isClosable: true,
            })
        );
    }

    const handleCancelChanges = () => {
        setStudInfo(oldStudInfo)
        setEditing(!editing)
    }

    const handleUpdatePhoto = (e) =>{
        setUpdatePhoto(!updatePhoto)
    }

    const handleFirstNameChange = (e) =>{
        setStudInfo(
            {
                ...studInfo,
                firstName:e.target.value
            }
        );
    }

    const handleLastNameChange = (e) =>{
        setStudInfo(
            {
                ...studInfo,
                lastName:e.target.value
            }
        );
    }

    const handleAddnewContact = () =>{
        setStudInfo(
            {
                ...studInfo,
                contacts:[
                    ...studInfo.contacts,
                    {
                        label:"Key",
                        data:"Value"
                    }
                ]
            }
        );
    }

    const handleBioChange = (e) =>{
        if(e.target.value.length > 255) return
        setStudInfo(
            {
                ...studInfo,
                biography:e.target.value
            }
        );
    }

    const handleContactsLabelChange = (e,idx) =>{
        setStudInfo(
            {
                ...studInfo,
                contacts:[
                    ...studInfo.contacts.slice(0,idx),
                    {
                        label:e.target.value,
                        data:studInfo.contacts[idx].data
                    },
                    ...studInfo.contacts.slice(idx+1)
                ]
            }
        );
    }

    const handleContactsDataChange = (e,idx) =>{
        setStudInfo(
            {
                ...studInfo,
                contacts:[
                    ...studInfo.contacts.slice(0,idx),
                    {
                        label:studInfo.contacts[idx].label,
                        data:e.target.value
                    },
                    ...studInfo.contacts.slice(idx+1)
                ]
            }
        );
    }
    
    return (

        <div className='studentBadge'>
            <Flex>
                <Flex marginRight='40'>
                    <Image borderRadius='full' boxSize='120px' src={studInfo.profilePictureUrl}/>
                    {editing && <BasicUsage/>}
                    <Box ml='6' marginRight={10}>
                        {editing?
                            <>
                            <Text fontWeight='bold'  fontSize='l'>
                                FirstName
                            </Text>
                            <Input variant='filled' type='text' contentEditable={true} value={studInfo.firstName} onChange={(e)=>handleFirstNameChange(e)}/>
                            <Text fontWeight='bold'  fontSize='l'>
                                LastName
                            </Text>
                            <Input variant='filled' type='text' contentEditable={true} value={studInfo.lastName} onChange={(e)=>handleLastNameChange(e)}/>                             
                            </>
                        :
                            <>
                            <Text fontWeight='bold'  fontSize='xl'>
                                {studInfo.firstName} {studInfo.lastName}
                            </Text>
                            <Text fontSize='md'>Computer Engineer</Text>
                            </>
                        }   
                    </Box>
                    <Stat>
                        <StatLabel>GPA</StatLabel>
                        <StatNumber>{studInfo.gpa}</StatNumber>
                    </Stat>
                </Flex>

                {editing?
                    <>
                        <Button colorScheme='teal' variant='solid' marginRight={10} onClick={handleSaveChanges}>
                            Save Changes
                        </Button>
                        <Button colorScheme='red' variant='solid' onClick={handleCancelChanges}>
                            Cancel
                        </Button> 
                    </>  
                    :
                    <Button colorScheme='gray' variant='solid' onClick={handleEditProfile}>
                        Edit Profile
                    </Button> 
                }
                
            </Flex>

            <Flex>
                <Card className='StudentInfo' borderRadius='10' zIndex={-1} marginRight={45}>
                    <CardHeader backgroundColor='#1F1F1F' color='white' borderRadius='10px 10px 0px 0px'>
                        <Heading size='md'>Student Info</Heading>
                    </CardHeader>

                    <CardBody backgroundColor='#f7f7f7' borderColor='#1F1F1F' borderWidth='thin' borderRadius='0px 0px 10px 10px'>
                        <Stack divider={<StackDivider />} spacing='4'>
                            <Box>
                                <Heading size='xs' textTransform='uppercase'>
                                    Biography
                                </Heading>
                                {editing?
                                    <Textarea variant='filled' contentEditable={true} value={studInfo.biography} onChange={handleBioChange}/>
                                    :
                                    <Text pt='2' fontSize='sm'>
                                        {studInfo.biography}
                                    </Text> 
                                }   
                            </Box>
                            <Box>
                                <Heading size='xs' textTransform='uppercase'>
                                    Major
                                </Heading>
                                <Text pt='2' fontSize='sm'>
                                    {studInfo.major}
                                </Text>
                            </Box>
                            <Box>
                                <Heading size='xs' textTransform='uppercase'>
                                    Minor
                                </Heading>
                                <Text pt='2' fontSize='sm'>
                                    {studInfo.minor}
                                </Text>
                            </Box>
                            <Box>
                                <Heading size='xs' textTransform='uppercase'>
                                    Number of Hours
                                </Heading>
                                <Text pt='2' fontSize='sm'>
                                    {studInfo.noOfHours}
                                </Text>
                            </Box>
                            <Box>
                                <Heading size='xs' textTransform='uppercase'>
                                    Level
                                </Heading>
                                <Text pt='2' fontSize='sm'>
                                    {studInfo.level}
                                </Text>
                            </Box>
                        </Stack>
                    </CardBody>
                </Card>

                <Card className='StudentInfo2' borderRadius='10'>
                    <CardHeader backgroundColor='#1F1F1F' color='white' borderRadius='10px 10px 0px 0px'>
                        <Heading size='md' display={'inline'} marginRight={40}>Student contacts</Heading>
                        {editing && 
                        <Button leftIcon={<AddIcon/>}  colorScheme='gray' variant='solid' size='sm' onClick={handleAddnewContact}>
                            Add new contact
                        </Button>}
                    </CardHeader>

                    <CardBody className='scrollableDiv' backgroundColor='#f7f7f7' borderColor='#1F1F1F' borderWidth='thin' borderRadius='0px 0px 10px 10px'>                        
                        <Stack divider={<StackDivider />} spacing='4'>
                            {
                                studInfo.contacts.map((contact,idx) =>{
                                    return(
                                        editing?
                                        <Box>
                                            <Input variant='filled' type='text' contentEditable={true} value={contact.label} onChange={(e)=>handleContactsLabelChange(e,idx)}/>
                                            <Input variant='flushed' paddingLeft={5} type='text' contentEditable={true} value={contact.data} onChange={(e)=>handleContactsDataChange(e,idx)}/>
                                        </Box>
                                        :
                                        <Box>
                                            <Heading size='xs' textTransform='uppercase' >
                                                {contact.label}
                                            </Heading>

                                            <Text pt='2' fontSize='sm'>
                                                {contact.data}
                                            </Text>
                                        </Box>
                                    )
                                })
                            }
                        </Stack>
                    </CardBody>
                </Card>
            </Flex>
        </div>
    )
}


