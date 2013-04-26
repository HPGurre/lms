package dk.itu.gsd.lms.integration.consumed.building.model;


public class MeasurementDto {
	
     private String id;
     private String bid;
     private String resource_uri;
	 private String uuid;
	 private String timestamp;
	 private String val;
	 
	 public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public String getResource_uri() {
		return resource_uri;
	}
	public void setResource_uri(String resource_uri) {
		this.resource_uri = resource_uri;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getUuid() {
	 return uuid;
	 }
	 public void setUuid(String uuid) {
	 this.uuid = uuid;
	 }
	 public String getTimestamp() {
		 return timestamp;
		 }
	 public String getValue() {
	 return val;
	 }
	 public void setValue(String value) {
	 this.val = value;
	 }
	@Override
	public String toString() {
		return "MeasurementDto [id=" + id + ", bid=" + bid + ", resource_uri="
				+ resource_uri + ", uuid=" + uuid + ", timestamp=" + timestamp
				+ ", value=" + val + "]";
	}

//	private Meta meta;
//	// public Objects objects;
//
//	@Override
//	public String toString() {
//		return "MeasurementDto [meta=" + meta + "]";
//	}

	// class Objects{
	// Objects() {
	// }
	// }
	// "meta": {
	// "limit": 2,
	// "next":
	// "/api/user/measurement/?limit=2&uuid=room-1-light-2-gain&offset=2&bid=5&timestamp__gte=2013-03-19",
	// "offset": 0,
	// "previous": null,
	// "total_count": 62677
	// },
	// "objects": [
	// {
	// "bid": 5,
	// "id": "160128116",
	// "resource_uri": "/api/user/measurement/160128116/",
	// "timestamp": "2013-03-19T18:55:01+00:00",
	// "uuid": "room-1-light-2-gain",
	// "val": 1
	// },
	// {
	// "bid": 5,
	// "id": "160130174",
	// "resource_uri": "/api/user/measurement/160130174/",
	// "timestamp": "2013-03-19T18:55:30+00:00",
	// "uuid": "room-1-light-2-gain",
	// "val": 1
	// }
	// ]

}

//class Meta {
//	private int limit;
//	private String next;
//	private int offset;
//	private String previous;
//	@Override
//	public String toString() {
//		return "Meta [limit=" + limit + ", next=" + next + ", offset=" + offset
//				+ ", previous=" + previous + ", total_count=" + total_count
//				+ "]";
//	}
//	private Long total_count;
//
//}

