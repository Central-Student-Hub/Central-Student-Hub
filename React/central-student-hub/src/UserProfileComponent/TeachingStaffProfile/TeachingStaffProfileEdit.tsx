import React, { useRef, useEffect } from "react";
import { TeachingStaffProfileInfo } from "../Models/TeachingStaffProfileInfo.tsx";
import { OfficeHours } from "../Models/OfficeHours";
import { ContactInfo } from "../Models/ContactInfo";

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
    const newContactData: ContactInfo[] = profile.contactData.map(cd => {
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
      <input onChange={onInputChange} id="tpi-edit-row" type="text" ref={keyRef} />
      <input onChange={onInputChange} id="tpi-edit-row" type="text" ref={valueRef} />
    </li>
  );
}

export function ContactInfoEdit({ profile, setProfile }: { profile: TeachingStaffProfileInfo, setProfile: Function }) {

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
        {profile.contactData.map(ci => <li><ContactInfoEditEntry contactInfo={ci} profile={profile} setProfile={setProfile} /></li>)}
      </ul>
      <button onClick={addEntry} id="tpi-edit-profile">Add Entry</button>
    </>
  );
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

export function OfficeHoursTableEdit({ profile, setProfile }) {

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
      {profile.officeHours.map(officeHour => <OfficeHoursRowEdit officeHour={officeHour} profile={profile} setProfile={setProfile} />)}
      <br />
      <button onClick={addEntry} id="tpi-edit-profile">Add Entry</button>
    </table>
  );
}

export function NameEdit({ profile, setProfile }) {
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

export function BiographyEdit({ profile, setProfile }) {
  const bioRef = useRef<HTMLTextAreaElement | null>(null);

  useEffect(() => {
    bioRef.current!.value = profile.biography;
  }, [profile]);

  function onInputChange() {
    setProfile({ ...profile, biography: bioRef.current!.value });
  }

  return <textarea placeholder="Bio" onChange={onInputChange} maxLength={ 255 } id="tpi-bio-edit" ref={bioRef} />
}
