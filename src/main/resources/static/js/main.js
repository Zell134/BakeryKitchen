let sock = new SockJS("http://localhost:8080/stomp");
let client = Stomp.over(sock);
client.connect({}, frame => {
    client.subscribe("/orders/new", payload => {
        let orders = document.getElementById('orders-container');
        let tr = document.createElement('tr');
        tr.setAttribute('class', 'table-row');
        tr.setAttribute('onclick', "window.location='/list/info/" + JSON.parse(payload.body).id + "'");
        orders.insertBefore(tr, orders.firstChild);

        let td = document.createElement('td');
        td.innerHTML = 'new';
        tr.appendChild(td);

        td = document.createElement('td');
        td.innerHTML = JSON.parse(payload.body).id;
        tr.appendChild(td);

        td = document.createElement('td');
        td.innerHTML = JSON.parse(payload.body).orderDate;
        tr.appendChild(td);

        td = document.createElement('td');
        td.innerHTML = 'Да';
        tr.appendChild(td);

        td = document.createElement('td');
        td.innerHTML = JSON.parse(payload.body).destination;
        tr.append(td);

    });
});
