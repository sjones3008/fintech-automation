import uuid

def valid_user_payload():
        uid = str(uuid.uuid4())[:8]
        return {
            "name": f"User_{uid}",
            "email": f"user_{uid}@example.com",
            "accountType": "premium"
        }

def valid_transaction_payload(user_id="123", recipient_id="456", amount=100.50):
        return {
            "userId": user_id,
            "amount": amount,
            "type": "transfer",
            "recipientId": recipient_id
        }