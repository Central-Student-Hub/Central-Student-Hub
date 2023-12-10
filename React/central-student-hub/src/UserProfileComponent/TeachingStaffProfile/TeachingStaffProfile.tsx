import React, { useEffect, useRef, useState } from 'react';
import { useParams } from 'react-router-dom';
import { TeachingStaffProfileInfo } from '../Models/TeachingStaffProfileInfo';
import { OfficeHours } from '../Models/OfficeHours';
import './TeachingStaffProfile.css';
import { BiographyEdit, ContactInfoEdit, NameEdit, OfficeHoursTableEdit } from './TeachingStaffProfileEdit.tsx';
import { ApiRequester } from '../../services/ApiRequester.ts';

function OfficeHoursRow({ officeHour }: { officeHour: OfficeHours }) {
  return <tr>
    <td>{ officeHour.weekday }</td>
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
  const apiRequester = new ApiRequester();

  const [profile, setProfile] = useState<TeachingStaffProfileInfo | null>(null);
  useEffect(() => {
    const fetchProfile = async () => await apiRequester.getTeachingStaffProfile();
    fetchProfile()
      .then((profile) => setProfile(profile))
      .catch((error) => console.error(error))
  }, []);

  if (profile == null) {
    return <h1>Loading...</h1>;
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
          <button onClick={() => setEditing(!editing) } id="tpi-edit-profile">{ editing ? "Apply" : "Edit Profile" }</button>
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
              { profile!.contactData.map(cd => <li><span>{ cd.key }:</span> { cd.value }</li>) }
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
