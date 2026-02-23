const { onDocumentCreated } = require("firebase-functions/v2/firestore");
const admin = require("firebase-admin");

admin.initializeApp();

exports.sendNotification = onDocumentCreated(
  {
    document: "news/{docId}",
    region: "asia-south1", // ⭐ ADD THIS
  },
  async (event) => {
    const data = event.data.data();

    const payload = {
      notification: {
        title: "📢 New Parish Update",
        body: data.title,
      },
      topic: "parish_updates",
    };

    await admin.messaging().send(payload);
  }
);