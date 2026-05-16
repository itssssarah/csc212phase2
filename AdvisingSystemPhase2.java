public class AdvisingSystemPhase2 implements IAdvisingSystemPhase2 {

  	Map<Integer, ILocation> locations;
    Map<Integer, IPerson> persons;
    Map<Integer, IEvent> events;
    
    static int event_counter = 10000;
    
    

	public AdvisingSystemPhase2() {
		locations = new BSTMap<Integer, ILocation>();
        persons = new BSTMap <Integer, IPerson>();
        events = new BSTMap<Integer, IEvent>();
        }

	
	
	/**
     * Returns all advisors in the system.
     */
	@Override
	public Set<IAdvisor> getAdvisors() {
		
		Set<IAdvisor> advisors = new BSTSet<IAdvisor>();
	    List<Integer> keys = persons.getKeys();
	    
	    if (keys.empty()) {
	        return advisors;
	    }
	    
	    keys.findFirst();
	    

	    while (true) {
	        IPerson person = persons.get(keys.retrieve());

	        if (person instanceof IAdvisor) { // if advisor
	            advisors.insert((IAdvisor) person);
	        }

	        if (keys.last()) {// last elment
	            break;
	        }

	        keys.findNext();
	    }

	    return advisors;
	}
	
	/**
     * Returns all students in the system.
     */
	@Override
	public Set<IStudent> getStudents() {
		
	Set<IStudent> students = new BSTSet<IStudent>();
	List<Integer> keys = persons.getKeys();
	
	
	if (keys.empty()) {
        return students; //return empty
    }
	
	keys.findFirst();
	
	while (true) {
        IPerson person = persons.get(keys.retrieve());

        if (person instanceof IStudent) {
            students.insert((IStudent) person);
        }

        if (keys.last()) {
            break;
        }

        keys.findNext();
    }

    return students;
}

	
	/**
     * Returns all locations in the system.
     */
	@Override
	public Set<ILocation> getLocations() {

	    Set<ILocation> locs = new BSTSet<ILocation>();
	    List<Integer> keys = locations.getKeys();

	    if (keys.empty()) {
	        return locs;
	    }

	    keys.findFirst();

	    while (true) {

	        ILocation location = locations.get(keys.retrieve());
	        locs.insert(location);

	        if (keys.last()) {
	            break;
	        }

	        keys.findNext();
	    }

	    return locs;
	}

	
	/**
     * Returns all meetings in the system.
     */
	@Override
	public Set<IMeeting> getMeetings() {
	    Set<IMeeting> meetings = new BSTSet<IMeeting>();
	    List<Integer> keys = events.getKeys();

	    
	    if (keys.empty()) {
	        return meetings;
	    }
	    
	    keys.findFirst();

	    while (true) {

	        IEvent event = events.get(keys.retrieve());

	        if (event instanceof IMeeting) {
	            meetings.insert((IMeeting) event);
	        }

	        if (keys.last()) {
	            break;
	        }

	        keys.findNext();
	    }

	    return meetings;
	}

	
	
	@Override
	public Set<IWorkshop> getWorkshops() {

	    Set<IWorkshop> workshops = new BSTSet<IWorkshop>();

	    List<Integer> keys = events.getKeys();

	    if (keys.empty()) {
	        return workshops;
	    }

	    keys.findFirst();

	    while (true) {

	        IEvent event = events.get(keys.retrieve());

	        if (event instanceof IWorkshop) {
	            workshops.insert((IWorkshop) event);
	        }

	        if (keys.last()) {
	            break;
	        }

	        keys.findNext();
	    }

	    return workshops;
	}
	

	@Override
	public Set<IEvent> getEvents() {

	    Set<IEvent> allEvents = new BSTSet<IEvent>();
	    List<Integer> keys = events.getKeys();

	    if (keys.empty()) {
	        return allEvents;
	    }

	    keys.findFirst();

	    while (true) {

	        IEvent event = events.get(keys.retrieve());
	        allEvents.insert(event);

	        if (keys.last()) {
	            break;
	        }

	        keys.findNext();
	    }

	    return allEvents;
	}
	
	//===========================================

	
	// Adds a new advisor to the system.
// Returns true if the advisor was added successfully,
// otherwise returns false if the advisor ID already exists.
	
    public boolean addAdvisor(IAdvisor advisor) {
    	 
             if ( persons.get(advisor.getId()) == null)
                 return persons.insert(advisor.getId(), advisor);
             return false;
         
    }
    
  // Finds an advisor using the advisor ID.
// Returns the advisor object if it exists, otherwise returns null.
	
    @Override
    public IAdvisor searchAdvisorById(int advisorId) {

        IPerson person = persons.get(advisorId);

        if (person != null && person instanceof IAdvisor) {
            return (IAdvisor) person;
        }

        return null;
    }

	
	
// Adds a new student to the system.
// Returns true if the student was added successfully,
// otherwise returns false if the ID already exists.

	
	@Override
	public boolean addStudent(IStudent student) {
		if (persons.get(student.getId()) == null)
            return persons.insert(student.getId(), student);
        return false;
	}

	@Override
	public IStudent searchStudentById(int studentId) {
		   IPerson person = persons.get(studentId);

		    if (person != null && person instanceof IStudent) {
		        return (IStudent) person;
		    }

		    return null;
	}
//+++++++++++++++++++++++++++++++++++++++++++===
	@Override
	public boolean deleteStudent(int studentId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addLocation(ILocation location) {
		  if (locations.get(location.getId()) == null)
                return locations.insert(location.getId(), location);
            return false;
	}

	@Override
	public ILocation searchLocationById(int locationId) {
		  return locations.get(locationId);
	}
	
	
//==============================================================

	
// Schedules a meeting between one advisor and one student.
// The meeting location will be the advisor office.
//
// Conditions:
// - both advisor and student must exist
// - there must be no time conflict for either one
//
// @param timeSlot meeting time slot
// @param advisorId advisor identifier
// @param studentId student identifier
// @return ID of the scheduled meeting
// @throws SchedulingException if scheduling is unsuccessful
	
	@Override
	public int scheduleMeeting(ITimeSlot timeSlot, int advisorId, int studentId) throws SchedulingException {

{
    IPerson studentPerson = this.searchStudentById(studentId);
    IPerson advisorPerson = this.searchAdvisorById(advisorId);

    if (studentPerson == null)
        throw new SchedulingException(ScheduleFailureReason.STUDENT_NOT_FOUND);

    if (advisorPerson == null)
        throw new SchedulingException(ScheduleFailureReason.ADVISOR_NOT_FOUND);

    if (persons.get(studentId).getSchedule().conflicts(timeSlot))
        throw new SchedulingException(ScheduleFailureReason.CONFLICT_STUDENT);

    if (persons.get(advisorId).getSchedule().conflicts(timeSlot))
        throw new SchedulingException(ScheduleFailureReason.CONFLICT_ADVISOR);

    // add meeting to student schedule
    ISchedule studentSchedule = studentPerson.getSchedule();
    studentSchedule.add(event_counter, timeSlot);
    persons.update(studentId, studentPerson);

    // add meeting to advisor schedule
    ISchedule advisorSchedule = advisorPerson.getSchedule();
    advisorSchedule.add(event_counter, timeSlot);
    persons.update(advisorId, advisorPerson);

    // create and store meeting event
    IMeeting meeting = new Meeting(event_counter++, timeSlot,
            ((Advisor) advisorPerson).getOffice(),
            advisorId, studentId);

    events.insert(meeting.getId(), meeting);

    return meeting.getId();
}
	}

	// Schedules a workshop for multiple students and advisors in one location.
//
// Conditions:
// - all students and advisors must exist
// - the location must exist and be reservable
// - there must be no schedule conflicts
// - the location should not already be occupied
//
// @param title workshop title
// @param timeSlot workshop time slot
// @param locationId location identifier
// @param advisorIds advisor identifiers
// @param studentIds student identifiers
// @return ID of the created workshop
// @throws SchedulingException if scheduling fails

@Override
public int scheduleWorkshop(String title, ITimeSlot timeSlot, int locationId,
                     int[] advisorIds, int[] studentIds)
        throws SchedulingException
{

    for (int index = 0; index < studentIds.length; index++)
    {
        IPerson studentPerson = this.searchStudentById(studentIds[index]);

        if (studentPerson == null)
            throw new SchedulingException(ScheduleFailureReason.STUDENT_NOT_FOUND);

        if (studentPerson.getSchedule().conflicts(timeSlot))
            throw new SchedulingException(ScheduleFailureReason.CONFLICT_STUDENT);
    }

    for (int index = 0; index < advisorIds.length; index++)
    {
        IPerson advisorPerson = this.searchAdvisorById(advisorIds[index]);

        if (advisorPerson == null)
            throw new SchedulingException(ScheduleFailureReason.ADVISOR_NOT_FOUND);

        if (advisorPerson.getSchedule().conflicts(timeSlot))
            throw new SchedulingException(ScheduleFailureReason.CONFLICT_ADVISOR);
    }

    ILocation location = locations.get(locationId);

    if (location == null)
        throw new SchedulingException(ScheduleFailureReason.LOCATION_NOT_FOUND);

    if (!location.isReservable())
        throw new SchedulingException(ScheduleFailureReason.LOCATION_NOT_RESERVABLE);

    if (location.getSchedule().conflicts(timeSlot))
        throw new SchedulingException(ScheduleFailureReason.CONFLICT_LOCATION);

    if (location.getCapacity() < studentIds.length)
        throw new SchedulingException(ScheduleFailureReason.CAPACITY_EXCEEDED);


    // add workshop to location schedule
    ISchedule locationSchedule = location.getSchedule();

    locationSchedule.add(event_counter, timeSlot);

    locations.update(locationId, location);


    // add workshop to student schedules
    Set<Integer> studentIdSet = new BSTSet<Integer>();

    for (int index = 0; index < studentIds.length; index++)
    {
        studentIdSet.insert(studentIds[index]);

        IStudent studentObject = (Student) persons.get(studentIds[index]);

        studentObject.getSchedule().add(event_counter, timeSlot);

        persons.update(studentIds[index], studentObject);
    }

    // add workshop to advisor schedules
    Set<Integer> advisorIdSet = new BSTSet<Integer>();

    for (int index = 0; index < advisorIds.length; index++)
    {
        advisorIdSet.insert(advisorIds[index]);

        IAdvisor advisorObject = (Advisor) persons.get(advisorIds[index]);

        advisorObject.getSchedule().add(event_counter, timeSlot);

        persons.update(advisorIds[index], advisorObject);
    }

    IWorkshop workshop = new Workshop(event_counter++, title,
            timeSlot, location, advisorIdSet, studentIdSet);

    events.insert(workshop.getId(), workshop);

    return workshop.getId();
}
	}
//-----------------------------------
	
// Cancels a meeting using its ID.
//
// @param meetingId meeting identifier
// @return true if the meeting was cancelled successfully,
// otherwise returns false

@Override
public boolean cancelMeeting(int meetingId)
{
    IMeeting meetingObject = (Meeting) events.get(meetingId);

    if ((meetingObject == null) || !(meetingObject instanceof Meeting))
        return false;

    events.remove(meetingId);

    IPerson studentPerson = persons.get(meetingObject.getStudentId());

    ISchedule studentSchedule = studentPerson.getSchedule();

    studentSchedule.remove(meetingId);

    persons.update(meetingObject.getStudentId(), studentPerson);

    IPerson advisorPerson = persons.get(meetingObject.getAdvisorId());

    ISchedule advisorSchedule = advisorPerson.getSchedule();

    advisorSchedule.remove(meetingId);

    persons.update(meetingObject.getAdvisorId(), advisorPerson);

    return true;
}
//-------------------------------------------
	
// Cancel a workshop using its ID.
//
// @param workshopId workshop identifier
// @return true if the workshop was removed successfully,
// otherwise returns false

@Override
public boolean cancelWorkshop(int workshopId)
{
    IWorkshop workshopObject = (Workshop) events.get(workshopId);

    if ((workshopObject == null) || !(workshopObject instanceof Workshop))
        return false;

    List<Integer> studentIds = workshopObject.getStudentIds().getKeys();

    if (!studentIds.empty())
        studentIds.findFirst();

    while (!studentIds.empty())
    {
        IPerson studentPerson = persons.get(studentIds.retrieve());

        studentPerson.getSchedule().remove(workshopId);

        persons.update(studentPerson.getId(), studentPerson);

        studentIds.remove();
    }

    List<Integer> advisorIds = workshopObject.getAdvisorIds().getKeys();

    if (!advisorIds.empty())
        advisorIds.findFirst();

    while (!advisorIds.empty())
    {
        IPerson advisorPerson = persons.get(advisorIds.retrieve());

        advisorPerson.getSchedule().remove(workshopId);

        persons.update(advisorPerson.getId(), advisorPerson);

        advisorIds.remove();
    }

    ILocation locationObject = locations.get(workshopObject.getLocation().getId());

    locationObject.getSchedule().remove(workshopId);

    locations.update(locationObject.getId(), locationObject);

    events.remove(workshopId);

    return true;
}
	}
//--------------------------------------------
	@Override
	public void addStudentToWorkshop(int workshopId, int studentId) throws SchedulingException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeStudentFromWorkshop(int workshopId, int studentId) throws SchedulingException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addAdvisorToWorkshop(int workshopId, int advisorId) throws SchedulingException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeAdvisorFromWorkshop(int workshopId, int advisorId) throws SchedulingException {
		// TODO Auto-generated method stub
		
	}

}

