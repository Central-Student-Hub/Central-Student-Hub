import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { TeachingStaffProfileInfo } from '../Models/TeachingStaffProfileInfo.ts';
import { OfficeHours } from '../Models/OfficeHours.ts';
import { BiographyEdit, ContactInfoEdit, NameEdit, OfficeHoursTableEdit } from './TeachingStaffProfileEdit.tsx';
import { UserProfileApi } from '../Services/UserProfileApi.ts';
import { Image } from '@chakra-ui/react'
import './TeachingStaffProfile.css';

function OfficeHoursRow({ officeHour }: { officeHour: OfficeHours }) {
  return <tr>
    <td>{ officeHour.weekDay }</td>
    <td>{ officeHour.fromTime }</td>
    <td>{ officeHour.toTime }</td>
    <td>{ officeHour.location }</td>
  </tr>
}

function OfficeHoursTable({ officeHours }: { officeHours: OfficeHours[] }) {
  return (
    <table id="tpi-office-hours-table">
      <tr>
        <th>Weekday</th>
        <th>From</th>
        <th>To</th>
        <th>Location</th>
      </tr>
      { officeHours.map(officeHour => <OfficeHoursRow officeHour={officeHour} />) }
    </table>
  );
}

export default function TeachingStaffProfile() {
  const { id } = useParams();
  const [editing, setEditing] = useState(false);
  const api = new UserProfileApi();

  const [profile, setProfile] = useState<TeachingStaffProfileInfo | null>(null);
  useEffect(() => {

    if (document.cookie === "") {
      window.location.href = 'http://localhost:3000/login';
    }

    if(id != null){
      const fetchProfile = async () => await api.getTeachingStaffProfileWithId(5);
      fetchProfile()
        .then((profile) => setProfile(profile))
        .catch((error) => console.error(error))
    }
    else{
      const fetchProfile = async () => await api.getTeachingStaffProfile();
      fetchProfile()
        .then((profile) => setProfile(profile))
        .catch((error) => console.error(error))
    }
  }, []);

  if (profile == null) {
    return <h1>Loading...</h1>;
  }

  async function handleEditClick() {
    setEditing(!editing);
    if (editing) {
      api.updateTeachingStaffProfile(profile!)
        .then()
        .catch((error) => console.error(error));
    }
  }

  return (
    <div id="tpi-container">
      <div>
        <div id="tpi-name-div">
          {/* {
            profile!.profilePictureUrl == "" ?
            <img
              id="tpi-profile-picture"
              src={ profile!.profilePictureUrl }
              alt={ `${profile!.firstName} ${profile!.lastName}` }
            />
            :
            <div id="tpi-profile-picture-placeholder"></div>
          } */}
          <Image borderRadius='full'
              boxSize='150px'
              src='https://bit.ly/dan-abramov'
              alt='Dan Abramov'
           />
          {/* <ProfilePictureEdit profile={ profile } setProfile={ setProfile } /> */}
          {
            editing ?
            <NameEdit profile={ profile } setProfile={ setProfile } />
            :
            <p id="tpi-name">{ profile!.firstName } { profile!.lastName }, { profile!.department }</p>
          }
          <button onClick={ handleEditClick } id="tpi-edit-profile">{ editing ? "Apply" : "Edit Profile" }</button>
        </div>

        <br />

        <div id="tpi-bio">
          {
            editing ?
            <BiographyEdit profile={ profile } setProfile={ setProfile } />
            :
            <p>{ profile!.biography }</p>
          }
        </div>

        <div id="tpi-divider"></div>

        <div id="tpi-contact-info-div">
          <h1>Contact Info</h1>
          {
            editing ?
            <ContactInfoEdit profile={ profile! } setProfile={ setProfile } />
            :
            <ul id="tpi-contact-info">
              { profile!.contacts.map(cd => <li><span>{ cd.label }:</span> { cd.data }</li>) }
            </ul>
          }
        </div>
      </div>

      <div>
        <h1>Office Hours</h1>
        {
          editing ?
          <OfficeHoursTableEdit profile={ profile } setProfile={ setProfile }/>
          :
          <OfficeHoursTable officeHours={ profile!.officeHours } />
        }
      </div>

    </div>
  );
}
