import React from "react";
import SearchBar from "./SearchBar";
import ContactItem from "./ContactItem";
import { useSelector } from "react-redux";

const ContactList = () => {
  const contactList = useSelector((state) => state.contactList);
  const keyword = useSelector(state=>state.keyword);
  return (
    <div>
      <SearchBar />
        {keyword==="" ? contactList.map((contact) => (
        <ContactItem contact={contact} />
      )) :
      contactList.filter(contact=>{
        if (contact.name.includes(keyword)) {
            return true;
        }
      }).map((contact) => (
        <ContactItem contact={contact} />
      ))
      }



      {}
    </div>
  );
};

export default ContactList;
