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
		// TODO Auto-generated method stub
		return null;
	}
	
	
//==============================================================
	@Override
	public int scheduleMeeting(ITimeSlot timeSlot, int advisorId, int studentId) throws SchedulingException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int scheduleWorkshop(String title, ITimeSlot timeSlot, int locationId, int[] advisorIds, int[] studentIds)
			throws SchedulingException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean cancelMeeting(int meetingId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean cancelWorkshop(int workshopId) {
		// TODO Auto-generated method stub
		return false;
	}

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

