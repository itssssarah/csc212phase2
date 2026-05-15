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


}
