from google.cloud import language_v1

client = language_v1.LanguageServiceClient()

texts = [
    "It’s always summer somewhere, and if you’re looking to get your beach on, a new list has ranked the top spots in the world for a tropical getaway.",
    "After losing the first three games in the series, the Rockets bounced back, winning Games 4 and 5. No NBA team has ever come back from a 3-0 deficit in the playoffs, so the Rockets will make history if they can pull off a full comeback and win the series.",
    "I bought these headphones last week and they sound horrible. The battery lasts 1 hour, and the noise cancellation is worst than I expected."
]

for text in texts:
    document = language_v1.Document(
        content=text,
        type_=language_v1.Document.Type.PLAIN_TEXT
    )

    sentiment = client.analyze_sentiment(
        request={"document": document}
    ).document_sentiment

    entities = client.analyze_entities(
        request={"document": document}
    ).entities

    print(f"Text: {text}")
    print(f"Sentiment: {sentiment.score}, Magnitude: {sentiment.magnitude}")

    for entity in entities:
        entity_type = language_v1.Entity.Type(entity.type_).name
        print(f"Entity name: {entity.name}, Type: {entity_type}, Salience score: {entity.salience}")

    print()