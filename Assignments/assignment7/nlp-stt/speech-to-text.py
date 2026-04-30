
from google.cloud.speech_v2 import SpeechClient
from google.cloud.speech_v2.types import cloud_speech

# YOUR PROJECT ID
PROJECT_ID = "project-86e5d1df-f58e-4fb6-b2c"

# YOUR LOCATION
LOCATION = "us-central1"

client = SpeechClient(
    client_options={"api_endpoint": f"{LOCATION}-speech.googleapis.com"}
)

# Add your audio file here! Test with Sandshrew.wav if you want. Keep your audio short (<30s)
with open("resources/Sandshrew.wav", "rb") as f:
    audio_content = f.read()

config = cloud_speech.RecognitionConfig(
    auto_decoding_config=cloud_speech.AutoDetectDecodingConfig(),
    language_codes=["en-US"],
    model="short",
)

request = cloud_speech.RecognizeRequest(
    recognizer=f"projects/{PROJECT_ID}/locations/{LOCATION}/recognizers/_",
    config=config,
    content=audio_content,
)

# Transcribes the audio into text
response = client.recognize(request=request)

for result in response.results:
    print(f"Transcript: {result.alternatives[0].transcript}")