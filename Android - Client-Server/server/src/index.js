const Koa = require('koa');
const app = new Koa();
const server = require('http').createServer(app.callback());
const WebSocket = require('ws');
const wss = new WebSocket.Server({server});
const Router = require('koa-router');
const cors = require('koa-cors');
const bodyParser = require('koa-bodyparser');
const convert = require('koa-convert');
const fs = require('fs');

app.use(bodyParser());

app.use(cors());

app.use(convert(function *(next) {
    const start = new Date();
    yield Promise.all(next);
    const ms = new Date() - start;
    console.log(`${start} ${this.method} ${this.url} - ${ms}ms`);
}));

const broadcast = (data) =>
    wss.clients.forEach((client) => {
        if (client.readyState === WebSocket.OPEN) {
            client.send(JSON.stringify(data));
        }
    });

const destinations = JSON.parse(fs.readFileSync('./destinations.json'));

const router = new Router();

router.get('/destinations', ctx => {
    ctx.response.body = destinations;
    ctx.response.status = 200;
});

router.post('/destination', ctx => {
    const destination = ctx.request.body;
    destination.id = destinations.length + 1;
    destinations.push(destination);
    fs.writeFileSync("./destinations.json", JSON.stringify(destinations, null, 2))
    ctx.response.body = destination;
    ctx.response.header =
    ctx.response.status = 201;
    broadcast(destination);
});

router.post('/destinations', ctx => {
    const newDestinations = ctx.request.body;
    newDestinations.forEach(destination => {
        destination.isUploaded = true
        destination.id = destinations.length + 1;
        destinations.push(destination)
    })
    fs.writeFileSync("./destinations.json", JSON.stringify(destinations, null, 2))
    ctx.response.body = newDestinations;
    ctx.response.status = 201;
    broadcast(newDestinations);
});

router.put('/destination', ctx => {
    const newDestination = ctx.request.body;
    let index = destinations.findIndex(obj => {
        return obj.id === newDestination.id
    })
    destinations[index].city = newDestination.city
    destinations[index].country = newDestination.country
    destinations[index].description = newDestination.description
    destinations[index].image = newDestination.image
    destinations[index].tourism = newDestination.tourism
    destinations[index].touristAttractions = newDestination.touristAttractions
    destinations[index].user = newDestination.user
    fs.writeFileSync("./destinations.json", JSON.stringify(destinations, null, 2))
    ctx.response.body = newDestination;
    ctx.response.status = 200;
    broadcast(newDestination);
});

router.del('/destinations/:id', ctx => {
    let destinationId = ctx.params['id']
    let index = destinations.findIndex(obj => {
        return obj.id == destinationId
    })
    destinations.splice(index, 1);
    fs.writeFileSync("./destinations.json", JSON.stringify(destinations, null, 2))
    ctx.response.status = 200;
});

app.use(router.routes());
app.use(router.allowedMethods());

server.listen(3000);