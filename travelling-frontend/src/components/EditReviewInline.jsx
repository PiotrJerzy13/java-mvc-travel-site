import { useState } from "react";
import { travellingApi } from "../services/travellingApi";

export default function EditReviewInline({ item, onSaved }) {
    const [open, setOpen] = useState(false);
    const [description, setDescription] = useState(item.description || "");
    const [rating, setRating] = useState(item.ratingSummary ?? "");
    const [saving, setSaving] = useState(false);

    const save = async () => {
        setSaving(true);
        try {
            await travellingApi.update(item.id, {
                destination: item.destination,
                date: item.date,
                description: description || null,
                rating: rating === "" ? null : Number(rating)
            });
            setOpen(false);
            onSaved?.();
        } catch (error) {
            alert("Failed to update: " + error.message);
        } finally {
            setSaving(false);
        }
    };

    if (!open) {
        return (
            <button onClick={() => setOpen(true)} style={styles.editButton}>
                {item.description ? "✏️ Edit Review" : "➕ Add Review"}
            </button>
        );
    }

    return (
        <div style={styles.editForm}>
            <textarea
                placeholder="Write your review..."
                value={description}
                onChange={(e) => setDescription(e.target.value)}
                rows={3}
                style={styles.textarea}
            />
            <input
                type="number"
                step="0.1"
                min="0"
                max="5"
                placeholder="Rating (0-5)"
                value={rating}
                onChange={(e) => setRating(e.target.value)}
                style={styles.input}
            />
            <div style={styles.buttonGroup}>
                <button onClick={save} disabled={saving} style={styles.saveButton}>
                    {saving ? '⏳ Saving...' : 'Save'}
                </button>
                <button onClick={() => setOpen(false)} disabled={saving} style={styles.cancelButton}>
                    Cancel
                </button>
            </div>
        </div>
    );
}

const styles = {
    editButton: {
        marginTop: '12px',
        padding: '8px 16px',
        background: 'transparent',
        color: '#4f46e5',
        border: '1px solid #4f46e5',
        borderRadius: '6px',
        fontSize: '14px',
        fontWeight: '500',
        cursor: 'pointer',
        transition: 'all 0.2s'
    },
    editForm: {
        marginTop: '16px',
        display: 'grid',
        gap: '12px'
    },
    textarea: {
        width: '100%',
        padding: '10px 12px',
        border: '1px solid #e0e0e0',
        borderRadius: '8px',
        fontSize: '14px',
        fontFamily: 'inherit',
        resize: 'vertical',
        outline: 'none'
    },
    input: {
        width: '100%',
        padding: '10px 12px',
        border: '1px solid #e0e0e0',
        borderRadius: '8px',
        fontSize: '14px',
        fontFamily: 'inherit',
        outline: 'none'
    },
    buttonGroup: {
        display: 'flex',
        gap: '8px'
    },
    saveButton: {
        padding: '8px 20px',
        background: '#10b981',
        color: 'white',
        border: 'none',
        borderRadius: '6px',
        fontSize: '14px',
        fontWeight: '500',
        cursor: 'pointer'
    },
    cancelButton: {
        padding: '8px 20px',
        background: '#f3f4f6',
        color: '#374151',
        border: 'none',
        borderRadius: '6px',
        fontSize: '14px',
        fontWeight: '500',
        cursor: 'pointer'
    }
};