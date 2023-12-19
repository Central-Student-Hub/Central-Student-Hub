import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { TeachingStaffProfileInfo } from '../Models/TeachingStaffProfileInfo';
import { OfficeHours } from '../Models/OfficeHours';
import './TeachingStaffProfile.css';
import { BiographyEdit, ContactInfoEdit, NameEdit, OfficeHoursTableEdit } from './TeachingStaffProfileEdit.tsx';
import { UserProfileApi } from '../../services/UserProfileApi.ts';

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
    const fetchProfile = async () => await api.getTeachingStaffProfile();
    fetchProfile()
      .then((profile) => setProfile(profile))
      .catch((error) => console.error(error))
  }, []);

  if (profile == null) {
    let tp: TeachingStaffProfileInfo = {
      firstName: "John",
      lastName: "Doe",
      department: "Computer Science",
      biography: "I am a professor at the University of Toronto.",
      profilePictureUrl: "https://www.w3schools.com/howto/img_avatar.png",
      contacts: [
        {
          label: "Email",
          data: "JohnDoe@gmail.com"
        },
        {
          label: "Phone",
          data: "416-123-4567"
        },
        {
          label: "Office",
          data: "BA123"
        },
      ],
      officeHours: [
        {
          id: 1,
          weekDay: "Monday",
          fromTime: "12:00:00",
          toTime: "13:00:00",
          location: "BA123"
        },
        {
          id: 2,
          weekDay: "Wednesday",
          fromTime: "12:00:00",
          toTime: "13:00:00",
          location: "BA123"
        },
        {
          id: 3,
          weekDay: "Friday",
          fromTime: "12:00:00",
          toTime: "13:00:00",
          location: "BA123"
        },
      ]
    }
    console.log(JSON.stringify(tp));
    return <h1>Loading...</h1>;
  }

  async function handleEditClick() {
    setEditing(!editing);
    if (editing)
      await api.updateTeachingStaffProfile(profile!)
  }

  return (
    <div id="tpi-container">
      <div>
        <div id="tpi-name-div">
          <img
            id="tpi-profile-picture"
            src={ profile!.profilePictureUrl }
            alt={ `${profile!.firstName} ${profile!.lastName}` }
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
