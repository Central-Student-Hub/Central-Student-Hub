import React, { useEffect, useRef, useState } from 'react';
import { useParams } from 'react-router-dom';
import { TeachingStaffProfileInfo } from '../Models/TeachingStaffProfileInfo';
import { OfficeHours } from '../Models/OfficeHours';
import './TeachingStaffProfile.css';

// function ProfilePictureEdit({ profile, setProfile }) {
//   const profilePictureRef = useRef<HTMLInputElement | null>(null);

//   useEffect(() => {
//     // profilePictureRef.current!.value = profile.profilePictureUrl;
//   }, [profile]);

//   function onInputChange() {
//     setProfile({ ...profile, profilePictureUrl: profilePictureRef.current!.value });
//   }

//   return <input type="file" />

//   // return <input placeholder="Profile Picture" onChange={onInputChange} id="tpi-edit-row" type="text" ref={ profilePictureRef } />
// }

function NameEdit({ profile, setProfile }) {
  const firstNameRef = useRef<HTMLInputElement | null>(null);
  const lastNameRef = useRef<HTMLInputElement | null>(null);
  const departmentRef = useRef<HTMLInputElement | null>(null);

  useEffect(() => {
    firstNameRef.current!.value = profile.firstName;
    lastNameRef.current!.value = profile.lastName;
    departmentRef.current!.value = profile.department;
  }, [profile]);

  function onInputChange() {
    setProfile({ ...profile, firstName: firstNameRef.current!.value, lastName: lastNameRef.current!.value, department: departmentRef.current!.value });
  }

  return (
    <div id="tpi-edit-name-div">
      <input placeholder="First Name" onChange={onInputChange} id="tpi-edit-row" type="text" ref={ firstNameRef } />
      <input placeholder="Last Name" onChange={onInputChange} id="tpi-edit-row" type="text" ref={ lastNameRef } />
      <input placeholder="Department" onChange={onInputChange} id="tpi-edit-row" type="text" ref={ departmentRef } />
    </div>
  );
}

function BiographyEdit({ profile, setProfile }) {
  const bioRef = useRef<HTMLTextAreaElement | null>(null);

  useEffect(() => {
    bioRef.current!.value = profile.biography;
  }, [profile]);

  function onInputChange() {
    setProfile({ ...profile, biography: bioRef.current!.value });
  }

  return <textarea placeholder="Bio" onChange={onInputChange} maxLength={ 255 } id="tpi-bio-edit" ref={bioRef} />
}

function OfficeHoursRow({ officeHour }: { officeHour: OfficeHours }) {
  return <tr>
    <td>{ officeHour.weekday }</td>
    <td>{ officeHour.fromTime }</td>
    <td>{ officeHour.toTime }</td>
    <td>{ officeHour.location }</td>
  </tr>
}

function OfficeHoursRowEdit({ officeHour, profile, setProfile }) {
  const weekDayRef = useRef<HTMLInputElement | null>(null);
  const fromTimeRef = useRef<HTMLInputElement | null>(null);
  const toTimeRef = useRef<HTMLInputElement | null>(null);
  const locationRef = useRef<HTMLInputElement | null>(null);

  useEffect(() => {
    if (officeHour === null) return;
    weekDayRef.current!.value = officeHour.weekday;
    fromTimeRef.current!.value = officeHour.fromTime == 0 ? '' : officeHour.fromTime.toString();
    toTimeRef.current!.value = officeHour.toTime == 0 ? '' : officeHour.toTime.toString();
    locationRef.current!.value = officeHour.location;
  }, [profile]);

  function onInputChange() {
    const newOfficeHours: OfficeHours[] = profile.officeHours.map(oh => {
      if (oh.id == officeHour.id) {
        return {
          ...oh,
          weekday: weekDayRef.current!.value,
          fromTime: parseInt(fromTimeRef.current!.value),
          toTime: parseInt(toTimeRef.current!.value),
          location: locationRef.current!.value
        };
      }
      return oh;
    });

    setProfile({ ...profile, officeHours: newOfficeHours });
  }

  function removeEntry() {
    const newOfficeHours: OfficeHours[] = profile.officeHours.filter(oh => oh.id != officeHour.id);
    setProfile({ ...profile, officeHours: newOfficeHours });
  }

  return (
    <>
      <tr>
        <td><button onClick={removeEntry} id="tpi-edit-profile">Remove Entry</button></td>
        <td><input onChange={onInputChange} id="tpi-edit-row" type="text" ref={ weekDayRef } /></td>
        <td><input onChange={onInputChange} id="tpi-edit-row" type="text" ref={ fromTimeRef } /></td>
        <td><input onChange={onInputChange} id="tpi-edit-row" type="text" ref={ toTimeRef } /></td>
        <td><input onChange={onInputChange} id="tpi-edit-row" type="text" ref={ locationRef } /></td>
      </tr>
    </>
  );
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

function ContactInfoEditEntry({ contactInfo, profile, setProfile }) {
  const keyRef = useRef<HTMLInputElement | null>(null);
  const valueRef = useRef<HTMLInputElement | null>(null);

  useEffect(() => {
    if (contactInfo === null) return;
    keyRef.current!.value = contactInfo.key;
    valueRef.current!.value = contactInfo.value;
  }, [profile]);

  function removeEntry() {
    const newContactData = profile.contactData.filter(cd => cd.key != contactInfo.key);
    setProfile({ ...profile, contactData: newContactData });
  }

  function onInputChange() {
    const newContactData = profile.contactData.map(cd => {
      if (cd.key == contactInfo.key) {
        return {
          key: keyRef.current!.value,
          value: valueRef.current!.value
        };
      }
      return cd;
    });

    const uniqueContactData = [...new Map(newContactData.map(item => [item["key"], item])).values()];
    setProfile({ ...profile, contactData: uniqueContactData });
  }

  return (
    <li id="tpi-contact-info-edit-li">
      <button onClick={removeEntry} id="tpi-edit-profile">Remove Entry</button>
      <input onChange={onInputChange} id="tpi-edit-row" type="text" ref={ keyRef } />
      <input onChange={onInputChange} id="tpi-edit-row" type="text" ref={ valueRef }/>
    </li>
  );
}

function ContactInfoEdit({ profile, setProfile }: { profile: TeachingStaffProfileInfo, setProfile: Function }) {

  function addEntry() {
    const newContactData = [...profile.contactData, {
      key: '',
      value: ''
    }];
    setProfile({ ...profile, contactData: newContactData });
  }

  return (
    <>
      <ul id="tpi-contact-info">
        { profile.contactData.map(ci => <li><ContactInfoEditEntry contactInfo={ ci } profile={ profile } setProfile={ setProfile } /></li>) }
      </ul>
      <button onClick={addEntry} id="tpi-edit-profile">Add Entry</button>
    </>
  );
}

function OfficeHoursTableEdit({ profile, setProfile }) {

  function addEntry() {
    const newOfficeHours = [...profile.officeHours, {
      id: Math.max(...(profile.officeHours.map(oh => oh.id))) + 1,
      weekday: '',
      fromTime: 0,
      toTime: 0,
      location: ''
    }];
    setProfile({ ...profile, officeHours: newOfficeHours });
  }

  return (
    <table id="tpi-office-hours-table">
        <tr>
          <th></th>
          <th>Weekday</th>
          <th>From</th>
          <th>To</th>
          <th>Location</th>
        </tr>
        { profile.officeHours.map(officeHour => <OfficeHoursRowEdit officeHour={officeHour} profile={ profile } setProfile={ setProfile } />) }
        <br />
        <button onClick={addEntry} id="tpi-edit-profile">Add Entry</button>
    </table>
  );
}

export default function TeachingStaffProfile() {
  const { id } = useParams();
  const [editing, setEditing] = useState(false);

  const [profile, setProfile] = useState<TeachingStaffProfileInfo>({
    teacherId: parseInt(id as string),
    firstName: 'John',
    lastName: 'Doe',
    biography: 'I am a teacher',
    profilePictureUrl: 'https://cdn.discordapp.com/attachments/1169703919698587750/1182441227514286242/Capture.PNG?ex=6584b535&is=65724035&hm=358277c48c0253159fa0c5d949adb685347527fcf4c5f7091c50edcc8c2998f1&',
    department: 'Computer Engineering',
    contactData: [
      {
        key: 'Email',
        value: 'JohnDoe@gmail.com'
      },
      {
        key: 'Phone Number',
        value: '12345678910'
      }
    ],
    
    officeHours: [
      {
        id: 4,
        weekday: 'Monday',
        fromTime: 11,
        toTime: 12,
        location: 'Room 1'
      },
      {
        id: 5,
        weekday: 'Tuesday',
        fromTime: 11,
        toTime: 12,
        location: 'Room 2'
      },
      {
        id: 6,
        weekday: 'Wednesday',
        fromTime: 9,
        toTime: 10,
        location: 'Room 3'
      },
      {
        id: 7,
        weekday: 'Thursday',
        fromTime: 14,
        toTime: 15,
        location: 'Room 4'
      }
    ]
  });

  // useEffect(() => {
  //   fetch(`http://localhost:8080/teaching-staff-profile/${id}`)
  //     .then(response => response.json())
  //     .then(data => setProfile(data))
  //     .catch(error => console.log(error));
  // }, [id]);

  return (
    <div id="tpi-container">
      <div>
        <div id="tpi-name-div">
          <img
            id="tpi-profile-picture"
            src={ profile.profilePictureUrl }
            alt={ `${profile.firstName} ${profile.lastName}` }
          />
          {/* <ProfilePictureEdit profile={ profile } setProfile={ setProfile } /> */}
          {
            editing ?
            <NameEdit profile={ profile } setProfile={ setProfile } />
            :
            <p id="tpi-name">{ profile.firstName } { profile.lastName }, { profile.department }</p>
          }
          <button onClick={() => setEditing(!editing) } id="tpi-edit-profile">{ editing ? "Apply" : "Edit Profile" }</button>
        </div>

        <div id="tpi-bio">
          {
            editing ?
            <BiographyEdit profile={ profile } setProfile={ setProfile } />
            :
            <p>{ profile.biography }</p>
          }
        </div>

        <div id="tpi-divider"></div>

        <div id="tpi-contact-info-div">
          <h1>Contact Info</h1>
          {
            editing ?
            <ContactInfoEdit profile={ profile } setProfile={ setProfile } />
            :
            <ul id="tpi-contact-info">
              { profile.contactData.map(cd => <li><span>{ cd.key }:</span> { cd.value }</li>) }
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
          <OfficeHoursTable officeHours={ profile.officeHours } />
        }
      </div>

    </div>
  );
}
