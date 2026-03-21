const { onDocumentCreated } = require("firebase-functions/v2/firestore");
const admin = require("firebase-admin");

admin.initializeApp();

// 🔔 NEWS
exports.sendNewsNotification = onDocumentCreated(
  "news/{docId}",
  async (event) => {

    const data = event.data.data();

    await admin.messaging().send({
      topic: "parish_updates",
      data: {
        title: "New Parish Update",
        message: data.title || "New update posted"
      }
    });
  }
);


// 🔔 EVENTS
exports.sendEventNotification = onDocumentCreated(
  "events/{docId}",
  async (event) => {

    const data = event.data.data();

    await admin.messaging().send({
      topic: "parish_updates",
      data: {
        title: "New Event",
        message: data.title || "New event added"
      }
    });
  }
);


// 🔔 GALLERY
exports.sendGalleryNotification = onDocumentCreated(
  "gallery/{docId}",
  async (event) => {

    const data = event.data.data();

    await admin.messaging().send({
      topic: "parish_updates",
      data: {
        title: "New Photos Uploaded",
        message: data.title || "New gallery update"
      }
    });
  }
);