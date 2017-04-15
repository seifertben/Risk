const MESSAGE_TYPE = {
  CONNECT: 0,
  UPDATE: 1,
  START: 2
};

let conn;
let myId;

const setup_matches = () => {

  conn = new WebSocket("ws://localhost:4567/matches");
  conn.onerror = err => {
    console.log('Connection error:', err);
  };

  conn.onmessage = msg => {
    const data = JSON.parse(msg.data);
    switch (data.type) {
      default:
        console.log('Unknown message type!', data.type);
        break;
      case MESSAGE_TYPE.CONNECT:
        console.log(data.id);
        myId = data.id;
        console.log(myId);
        break;
      case MESSAGE_TYPE.UPDATE:
		break;
    }
  };
}

const start_game = start => {
    console.log(myId);
    let mess = {"type" : "2", "partners": myId};
    conn.send(JSON.stringify(mess));
}
    $(document).keypress(event => {
        // 13 is the key code for the Enter key
        if (event.which == 13) {
        start_game();
        }
        });