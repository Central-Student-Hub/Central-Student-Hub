import React, { useRef, useEffect } from "react";
import { TeachingStaffProfileInfo } from "../Models/TeachingStaffProfileInfo.ts";
import { OfficeHours } from "../Models/OfficeHours.ts";
import { ContactInfo } from "../Models/ContactInfo.ts";

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
    keyRef.current!.value = contactInfo.label;
    valueRef.current!.value = contactInfo.data;
  }, [profile]);

  function removeEntry() {
    const newContactData = profile.contacts.filter(cd => cd.label != contactInfo.label);
    setProfile({ ...profile, contacts: newContactData });
  }

  function onInputChange() {
    const newContactData: ContactInfo[] = profile.contacts.map(cd => {
      if (cd.label == contactInfo.label) {
        return {
          label: keyRef.current!.value,
          data: valueRef.current!.value
        };
      }
      return cd;
    });

    const uniqueContactData = [...new Map(newContactData.map(item => [item["label"], item])).values()];
    setProfile({ ...profile, contacts: uniqueContactData });
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
    const newContactData = [...profile.contacts, {
      label: '',
      data: ''
    }];
    setProfile({ ...profile, contacts: newContactData });
  }

  return (
    <>
      <ul id="tpi-contact-info">
        {profile.contacts.map(ci => <li><ContactInfoEditEntry contactInfo={ci} profile={profile} setProfile={setProfile} /></li>)}
      </ul>
      <br />
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
    weekDayRef.current!.value = officeHour.weekDay;
    fromTimeRef.current!.value = officeHour.fromTime;
    toTimeRef.current!.value = officeHour.toTime;
    locationRef.current!.value = officeHour.location;
  }, [profile]);

  function onInputChange() {
    const newOfficeHours: OfficeHours[] = profile.officeHours.map(oh => {
      if (oh.officeHourId == officeHour.officeHourId) {
        return {
          ...oh,
          weekDay: weekDayRef.current!.value,
          fromTime: fromTimeRef.current!.value,
          toTime: toTimeRef.current!.value,
          location: locationRef.current!.value
        };
      }
      return oh;
    });

    setProfile({ ...profile, officeHours: newOfficeHours });
  }

  function removeEntry() {
    const newOfficeHours: OfficeHours[] = profile.officeHours.filter(oh => oh.officeHourId != officeHour.officeHourId);
    setProfile({ ...profile, officeHours: newOfficeHours });
  }

  return (
    <>
      <tr>
        <td><button onClick={removeEntry} id="tpi-edit-profile">Remove Entry</button></td>
        <td><input onChange={onInputChange} id="tpi-edit-row" type="text" ref={ weekDayRef } /></td>
        <td><input onChange={onInputChange} id="tpi-edit-row" type="time" ref={ fromTimeRef } /></td>
        <td><input onChange={onInputChange} id="tpi-edit-row" type="time" ref={ toTimeRef } /></td>
        <td><input onChange={onInputChange} id="tpi-edit-row" type="text" ref={ locationRef } /></td>
      </tr>
    </>
  );
}

export function OfficeHoursTableEdit({ profile, setProfile }: { profile: TeachingStaffProfileInfo, setProfile: Function }) {

  function addEntry() {
    const newOfficeHours: OfficeHours[] = [...profile.officeHours, {
      officeHourId: Math.max(...(profile.officeHours.map(oh => oh.officeHourId))) == -Infinity ? 0 : Math.max(...(profile.officeHours.map(oh => oh.officeHourId))) + 1,
      weekDay: '',
      fromTime: '',
      toTime: '',
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
