const { app, BrowserWindow } = require('electron');
const { exec } = require('child_process');

let win;

function createWindow() {
    win = new BrowserWindow({
        width: 1200,
        height: 800
    });

    win.loadURL('http://localhost:8081');
}

function startBackend() {
    const process = exec('java -jar backend/notesapp-1.0.0.jar');

    process.stdout.on('data', (data) => {
        console.log(`OUT: ${data}`);
    });

    process.stderr.on('data', (data) => {
        console.error(`ERROR: ${data}`);
    });

    process.on('close', (code) => {
        console.log(`Backend exited with code ${code}`);
    });
}

app.whenReady().then(() => {
    startBackend();
    setTimeout(createWindow, 4000);
});